/*
Description:    Multi threaded test program for arrayStack implementation (see files arrayStack.h and arrayStack.c)
                It starts two threads filling and two threads consuming a dynamically growing and shrinking
                array based stack implementation.
Note:           It is likely that the program does not run through but crashes due to memory corruption caused by
                not synchronized memory allocation and freeing.
                Expect to see errors such as:
                - double free or corruption
                - realloc(): invalid next size
                - Segmentation fault
Author:         Lukas Ith
Last update:    2022-10-31
Build:          make
Run:            ./arrayStackTest

Task:           Make the implementation of arrayStack.c thread safe.
                - Test program runs without crashing.
                - Test program does not output any inconsistency (no output means no error).
                Try to keep the locks fine grained.
                Which function(s) do not need locking and why?.

Additional tasks:
                - Make sure you have an equal number of locks and unlocks in your code
                - Improve the function consume() in arrayStackTest.c so it does not spin wait if the stack is empty.
*/

#include <stdio.h>
#include <pthread.h>
#include <sched.h>
#include "arrayStack.h"

#define TEST_SIZE 1000000

struct arrayStack* stack;  // stack shared by all threads

pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;  // threads for consumer locking

void *fill()  // start function of threads filling the stack
{
    for(int i = 0; i < TEST_SIZE; i++)
    {
        arrayStack_push(stack, i);
    }
    return NULL;
}

void *consume(void* result)  // start function of threads consuming the stack
{
    int element;
    for(int i = 0; i < TEST_SIZE; i++)
    {
        while(arrayStack_pop(stack, &element) != 0) {
            sched_yield();
        }
        ((int*)result)[element]++;
    }
    return NULL;
}

int main(int argc, char** argv)
{
    stack = arrayStack_init();

    // arrays for book keeping: which result value has ben read how many times?
    int result1[TEST_SIZE] = {0};  // array fot first consuming thread
    int result2[TEST_SIZE] = {0};  // array for second consuming thread

    pthread_t fill1, fill2, consume1, consume2;

    // start test
    pthread_create(&fill1, NULL, fill, NULL);
    pthread_create(&fill2, NULL, fill, NULL);
    pthread_create(&consume1, NULL, consume, result1);
    pthread_create(&consume2, NULL, consume, result2);

    // wait foll arr threads to finish their work
    pthread_join(fill1, NULL);
    pthread_join(fill2, NULL);
    pthread_join(consume1, NULL);
    pthread_join(consume2, NULL);

    // check for consistency based on book keeping arrays
    for(int i = 0; i < TEST_SIZE; i++)
    {
        int count = result1[i] + result2[i];
        if(count != 2)
        {
            printf("%d: %d\n", i, count);
        }
    }

    return 0;
}
