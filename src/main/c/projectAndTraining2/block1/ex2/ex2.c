#include <stdio.h>
#include <stdlib.h>

int max(int a, int b) { return (a > b) ? a : b; }

int backpack(int values[], int weights[], int capacity, int n)
{
    if (capacity == 0 || n == 0) return 0;
    if (weights[n - 1] > capacity)
    {
        return backpack(values, weights, capacity, n - 1);
    } 
    else
    {
        return max(
            values[n - 1] + backpack(values, weights, capacity - weights[n - 1], n - 1),
            backpack(values, weights, capacity, n - 1));
    }
}

int main(void)
{
    int capacity = 16;
    int values[] = {6, 3, 5, 4, 4, 2};
    int weights[] = {2, 3, 7, 4, 4, 2};
    int n = sizeof(values) / sizeof(values[0]);
    printf("%d\n", backpack(values, weights, capacity, n));
    return 0;
}
