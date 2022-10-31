EXEC = arrayStackTest

CC = gcc
CFLAGS = -c -Wall -pedantic -O0 -g
LDFLAGS = -pthread

OBJECTS = $(patsubst %.c, %.o, $(wildcard *.c))  # .o filenames for all .c files

all: $(EXEC)

$(EXEC): $(OBJECTS)  # link all .o to EXEC
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c  # compile a .c file to .o
	$(CC) $(CFLAGS) -o $@ $<

clear: clean
	rm -f $(EXEC)

clean:
	rm -f *.o

.PHONY: clear, clean