#include <stdio.h>
#include <limits.h>
#include <string.h>
#include <stdlib.h>

int smallest_problem(int *numbers, int count) {
    int x = INT_MAX;

    for(int i = 0; i < count; i++) {
        if(numbers[i] < x) {
            x = numbers[i];
        }
    }
    return x;
}


void store_value(int *store, int word_one_pos, int word_two_pos, int value, int longer_word_length) {
    store[longer_word_length * (word_one_pos - 1) + (word_two_pos - 1)] = value;
}

int get_value(int *store, int word_one_pos, int word_two_pos, int longer_word_length) {
    return store[longer_word_length * (word_one_pos - 1) + (word_two_pos - 1)];
}

int edit_distance(int *store, char *word_one, char *word_two, int word_one_pos, int word_two_pos, int longer_word_length) {
    if(word_one_pos == 0) {
        store_value(store, word_one_pos, word_two_pos, word_two_pos, longer_word_length);
        return word_two_pos;
    }

    if(word_two_pos == 0) {
        store_value(store, word_one_pos, word_two_pos, word_one_pos, longer_word_length);
        return word_one_pos;
    }

    // print store
    for(int i = 0; i < strlen(word_one); i++) {
        for(int j = 0; j < strlen(word_two); j++) {
            printf("%d ", store[strlen(word_one) * j + i]);
        }
        printf("\n");
    }

    int storeValue = get_value(store, word_one_pos, word_two_pos, longer_word_length);
    printf("searching %d %d -> %d\n", word_one_pos, word_two_pos, storeValue);

    if(storeValue > -1) {
        return storeValue;
    }

    if(word_one[word_one_pos - 1] == word_two[word_two_pos - 1]) {
        int equal_distance = edit_distance(store, word_one, word_two, word_one_pos - 1, word_two_pos - 1, longer_word_length);
        store_value(store, word_one_pos, word_two_pos, equal_distance, longer_word_length);
        return equal_distance;
    }

    int try_insert_distance = edit_distance(store, word_one, word_two, word_one_pos, word_two_pos - 1, longer_word_length);
    int try_remove_distance = edit_distance(store, word_one, word_two, word_one_pos - 1, word_two_pos, longer_word_length);
    int try_replace_distance = edit_distance(store, word_one, word_two, word_one_pos - 1, word_two_pos - 1, longer_word_length);

    int problem_sizes[] = {try_insert_distance, try_remove_distance, try_replace_distance};
    int smallest_problem_size = 1 + smallest_problem(problem_sizes, 3);

    store_value(store, word_one_pos, word_two_pos, smallest_problem_size, longer_word_length);

    return smallest_problem_size;
}

int main(void) {
    char *word_two = "Physiotherapie";
    char *word_one = "Psychiater";

    int *results_store = malloc(strlen(word_one) * strlen(word_two) * sizeof(int));
    for (int i = 0; i < strlen(word_one) * strlen(word_two); i++) {
        results_store[i] = -1;
    }

    int result;
    if(strlen(word_one) > strlen(word_two)) {
        result = edit_distance(results_store, word_two, word_one, strlen(word_two), strlen(word_one), strlen(word_one));
    } else {
        result = edit_distance(results_store, word_one, word_two, strlen(word_one), strlen(word_two), strlen(word_two));
    }

    printf("the result is %d", result);
    free(results_store);
}

