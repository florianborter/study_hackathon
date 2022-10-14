#include <stdio.h>
#include <stdlib.h>

int max(int a, int b) { return (a > b) ? a : b; }

int **generateTable(int n, int capacity)
{
    int **calculatedValues = (int **)malloc(n * sizeof(int *));
    for (int i = 0; i < n; i++)
    {
        calculatedValues[i] = (int *)malloc((capacity + 1) * sizeof(int));
        for (int j = 0; j <= capacity; j++)
        {
            calculatedValues[i][j] = -1;
        }
    }
    return calculatedValues;
}

int backpack(int values[], int weights[], int capacity, int n, int **calculatedValues)
{
    if (n < 0 || capacity < 0)
        return 0;
    if (calculatedValues[n][capacity] != -1)
        return calculatedValues[n][capacity];
    if (weights[n] > capacity)
    {
        calculatedValues[n][capacity] = backpack(values, weights, capacity, n - 1, calculatedValues);
        return calculatedValues[n][capacity];
    }
    else
    {
        calculatedValues[n][capacity] = max(
            values[n] + backpack(values, weights, capacity - weights[n], n - 1, calculatedValues),
            backpack(values, weights, capacity, n - 1, calculatedValues));
        return calculatedValues[n][capacity];
    }
}

int main(void)
{
    int capacity = 16;
    int values[] = {6, 3, 5, 4, 5, 2};
    int weights[] = {2, 3, 7, 4, 4, 2};
    int n = sizeof(values) / sizeof(values[0]);
    int** table = generateTable(n, capacity);
    printf("the result is %d\n", backpack(values, weights, capacity, n - 1, table));
    
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j <= capacity; j++)
        {
            printf("\t[%d]", table[i][j]);
        }
        printf("\n");
    }
    return 0;
}
