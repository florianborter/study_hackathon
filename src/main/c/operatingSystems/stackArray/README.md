# Exercise to make a data structure thread safe
Given is the implementation of arrayStack (Header arrayStack.h and implementation arrayStack.c).
- The stack is array based and grows and shrinks as needed (by doubling and halving the array of elements).
- Note that this implementation is NOT thread safe!

## Test program
arrayStackTest.c contains a multi threaded test program for arrayStack implementation
                It starts two threads filling and two threads consuming a dynamically growing and shrinking
                array based stack implementation.
Note:           It is likely that the program does not run through but crashes due to memory corruption caused by
                not synchronized memory allocation and freeing.
                Expect to see errors such as:
                - double free or corruption
                - realloc(): invalid next size
                - Segmentation fault

# Task
Make the implementation of arrayStack.c thread safe.
Test it with the given test program (arrayStackTest.c):
- Test program runs without crashing.
- Test program does not output any inconsistency (no output means no error).

Try to keep the locks fine grained.

Which function(s) do not need locking and why?.

## Additional tasks
- Make sure you have an equal number of locks and unlocks in your code
- Improve the function consume() in arrayStackTest.c so it does not spin wait if the stack is empty.
