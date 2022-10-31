/*
Description:    Implementation of arrayStack
                The stack is array based and grows and shrinks as needed (by doubling and halving the array of elements)
                Note that this implementation is NOT thread safe!
Author:         Lukas Ith
Last update:    2022-10-30
*/
#include <pthread.h>
#include <stdio.h>

#include "arrayStack.h"

#define INIT_CAPACITY 16  // start capacity of the stack

struct arrayStack  // struct representing the array
{
    int* array;                     // array of elements
    size_t init_capacity;           // initial capacity (smallest value to shrink to)
    size_t capacity;                // current capacity
    int count;                      // number of elements on the stack
    pthread_mutex_t lock;           // Lock
};

struct arrayStack* arrayStack_init()
{
    // allocate memory for struct
    struct arrayStack* stack = (struct arrayStack*) malloc (sizeof(struct arrayStack));
    if(stack == NULL)
    {
        return NULL;
    }

    // allocate initial array
    stack->init_capacity = INIT_CAPACITY;
    stack->array = (int*) malloc(stack->init_capacity * sizeof(int));
    if(stack->array == NULL)
    {
        return NULL;
    }

    stack->capacity = stack->init_capacity; 
    stack->count = 0;
    return stack;
}

int arrayStack_push(struct arrayStack* stack, int value)
{
    if(stack == NULL)
    {
        return ARGUMENT_ERROR;
    }
    if(stack->array == NULL)
    {
        return UNINITIALIZED_ERROR;
    }
    
    int return_code;

    pthread_mutex_lock(&stack->lock);
    if(stack->count < stack->capacity) { // capacity limit reached?
        stack->array[stack->count++] = value;  // place value on top of stack
        return_code = SUCCESS;
    } else {
        int newCapacity = stack->capacity * 2;
        int* newArray = (int*) realloc(stack->array, newCapacity * sizeof(int));
        if(newArray == NULL) {
            return_code = ALLOC_ERROR;
        } else {
            stack->array = newArray;
            stack->capacity = newCapacity;
            stack->array[stack->count++] = value;  // place value on top of stack
            return_code = SUCCESS;
        }
    }

    pthread_mutex_unlock(&stack->lock);
    return return_code;
    
}

int arrayStack_pop(struct arrayStack* stack, int* out)
{
    if(stack == NULL)
    {
        return ARGUMENT_ERROR;
    }
    if(stack->array == NULL)
    {
        return UNINITIALIZED_ERROR;
    }

    pthread_mutex_lock(&stack->lock);
    if(stack->count == 0)  // stack empty?
    {
        pthread_mutex_unlock(&stack->lock);
        return EMPTY_ERROR;
    }

    *out = stack->array[--(stack->count)];  // pop top element

    int halfCapacity = stack->capacity / 2;
    if(stack->count < halfCapacity
        && stack->capacity > stack->init_capacity)  // shrinking?
    {
        int* newArray = (int*) realloc(stack->array, halfCapacity * sizeof(int));
        if(newArray == NULL)
        {
            pthread_mutex_unlock(&stack->lock);
            return ALLOC_ERROR;
        }
        stack->array = newArray;
        stack->capacity = halfCapacity;
    }

    pthread_mutex_unlock(&stack->lock);
    return SUCCESS;
}

int arrayStack_top(struct arrayStack* stack, int* out)
{
    if(stack == NULL)
    {
        return ARGUMENT_ERROR;
    }
    if(stack->array == NULL)
    {
        return UNINITIALIZED_ERROR;
    }

    if(stack->count == 0)  // stack empty?
    {
        return EMPTY_ERROR;
    }
    *out = stack->array[stack->count - 1];
    return SUCCESS;
}

int arrayStack_destroy(struct arrayStack* stack)
{
    if(stack == NULL)
    {
        return ARGUMENT_ERROR;
    }

    if(stack->array != NULL)  // are we getting an instance not yet destroyed?
    {
        free(stack->array);
        stack->array = NULL;
        free(stack);
    }
    return SUCCESS;
}
