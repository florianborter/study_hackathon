/*
Description:    Header file for an array based stack
Author:         Lukas Ith
Last update:    2022-10-30
*/

#pragma once

#include <stdlib.h>

// error codes
#define SUCCESS 0
#define ARGUMENT_ERROR 1
#define ALLOC_ERROR 2
#define EMPTY_ERROR 3
#define UNINITIALIZED_ERROR 4
#define INITIALIZED_ERROR 5

// opaque struct representing a stack instance
struct arrayStack;

// initialize an instance of arrayStack
// allocates memory: use arrayStack_destroy to free the memory used by an instance
struct arrayStack* arrayStack_init();

// push value on the stack
int arrayStack_push(struct arrayStack* stack, int value);

// pop a value from the stack (if available)
int arrayStack_pop(struct arrayStack* stack, int* out);

// peek top element (if available)
int arrayStack_top(struct arrayStack* stack, int* out);

// free memory used by stack instance
int arrayStack_destroy(struct arrayStack* stack);
