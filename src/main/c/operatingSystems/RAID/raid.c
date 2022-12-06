/*
Description:    RAID 4 exercise
                Implement the missing functions write_raid() and read_raid()
                so that the test in main() passes.
Author:         Lukas Ith
Last change:    2022-11-29

Note:           Reading and writing must be possible after one of the disks failed.
                A disk failure is indicated by setting its pointer in the disks array to NULL.
                It is also possible that the disk containing the parity data fails.
                Change the test data as you fit.

Remarks:        With the the values TOTAL_SIZE 4096, BLOCK_SIZE 512 and NBR_DISKS 5 the layout is as follows:
        Disk        0      1      2      3      4
                +------+------+------+------+------+
        Data    |    0 |  512 | 1024 | 1536 |parity|  stripe 0
        Range   |  511 | 1023 | 1535 | 2047 |parity|
                +------+------+------+------+------+
        Data    | 2048 | 2560 | 3072 | 3584 |parity|  stripe 1
        Range   | 2559 | 3071 | 3583 | 4095 |parity|
                +------+------+------+------+------+
*/

#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <assert.h>

#define TOTAL_SIZE 4096
#define BLOCK_SIZE 512
#define NBR_DISKS 5
#define FAIL_DISK 1

uint8_t* disks[NBR_DISKS];

// Initializes a raid array with NBR_DISK
// 0..NBR_DISKS - 2 are data disks NBR_DISKS - 1 the parity disk
void init_raid(void);

// Write a byte to the raid array
// value    - the byte to write
// position - the position to write to
// The function calculates which disks to write to and writes parity
// NOTE: One of the disks (data or parity) may be offline
void write_raid(uint8_t value, long position);

// Read a byte from thr raid array
// position - the position to read from 
// returns the vale read at position
// NOTE: One of the disks (data or parity) may be offline
uint8_t read_raid(long position);


// test program
int main(int argc, char *argv[])
{
    FILE* input;
    long pos = 0;
    char ch;

    init_raid();  // set up raid
    assert(NULL !=(input = fopen("testdata", "r")));  // open test data

    // write first part to raid
    while(EOF != (ch = fgetc(input)))
    {
        write_raid(ch, pos);
        if(++pos == TOTAL_SIZE / 2) {
            break;
        }
    }

    // kill a disk
    free(disks[FAIL_DISK]);
    disks[FAIL_DISK] = NULL;
    
    // continue writing second part to raid
    while(EOF != (ch = fgetc(input)))
    {
        write_raid(ch, pos);
        if(++pos == TOTAL_SIZE) {
            break;
        }
    }

    // compare file content with data saved in raid
    fseek(input, 0, SEEK_SET);  // return to start of dile
    pos = 0;
    while(EOF != (ch = fgetc(input)))
    {
        char read = read_raid(pos++);
        printf("read: %d\n",read);
        printf("ch: %d\n",ch);
        assert(read == ch);
    }

    // cleanup
    for (int i = 0; i < NBR_DISKS; i++) {
        if(disks[i] != NULL) {
            free(disks[i]);
            disks[i] = NULL;
        }
    }

    return 0;
}

void init_raid(void)
{
    // validate configuration
    assert(NBR_DISKS > 1);  // at least two disks (one for data, one for parity)
    assert(TOTAL_SIZE % (NBR_DISKS -1 ) == 0);  // size is even distributable to disks
    assert((TOTAL_SIZE / (NBR_DISKS -1)) % BLOCK_SIZE == 0);  // single disk is dividable by block size
    assert(FAIL_DISK <= NBR_DISKS);  // disk to fail is one of the raid array

    // allocate memory to simulate disks
    for(int i = 0; i < NBR_DISKS; i++) {
        uint8_t* disk;
        assert(NULL !=(disk = (uint8_t*)calloc(TOTAL_SIZE / (NBR_DISKS - 1), sizeof(uint8_t))));
        disks[i] = disk;
    }
}

void write_raid(uint8_t value, long position) {
    int disk = position / BLOCK_SIZE % (NBR_DISKS-1);
    int offset = position % BLOCK_SIZE;

    if(position >= (NBR_DISKS-1) * BLOCK_SIZE) {
        offset += BLOCK_SIZE;
    }

    if(disks[disk] != NULL) {
        disks[disk][offset] = value;
    }

    disks[NBR_DISKS - 1][offset] ^= value;
}

uint8_t read_raid(long position) {
    int disk = position / BLOCK_SIZE % (NBR_DISKS-1);
    int offset = position % BLOCK_SIZE;

    if(position >= (NBR_DISKS-1) * BLOCK_SIZE) {
        offset += BLOCK_SIZE;
    }
    if(disks[disk] != NULL) {
        return disks[disk][offset];
    }
    int parity = 0;
    for(int i = 0; i < NBR_DISKS; i++) {
        if(i != disk) {
            parity ^= disks[i][offset];
        }
    }

    return parity;
}