#include <stdio.h>
#include <stdlib.h>

/*
Maman 23
Lidor Blustein
314993460
*/

/* The default return value */
#define OK 0
/* Value of arg count needed */
#define ARGC 2
/* Index of the file in argv */
#define ARGF 1
/* Value of good scanf */
#define SUCCESS 1

/* Returns an int array of numbers made by fibonacci function given a user input n 
(fib(n) = fib(n-1) + fib(n-2), when fib(0) = fib(1) = 1) */
int *get_fibon(int n);


/* The program gets the user's input from the command line,
the first argument is the program, and the second is a file name.
It uses get_fibon function to make the array of values for a 
circular linked-list. 
Then, prints the values and saves them to the file with a short explanation.
*/
int main(int argc, char *argv[]) {

    /* Variable declarations */
    /* n - number of fib elements, i - index for loops */
    int n, i;
    /* the int array of fib numbers */
    int *fib_list;
    /* file to write to */
    FILE *ifp;
    /* making the linked-list elements */
    struct l_list {
        int val;
        struct l_list *next;
    };
    typedef struct l_list Link;
    /* making the linked-list head pointer and another pointer to move */
    Link *head, *curr;

    /* if the number of arguments is not 2 (no file added or more than 1 file given)
    print error and end program */
    if(argc != ARGC){
        fprintf(stderr, "Argument number is not valid\n");
        return !OK;
    }
    /* if the file wasn't open - print error and end program */
    if ((ifp=fopen(argv[ARGF],"w")) == NULL){
 	printf("Cannot open file %s\n", argv[ARGF]);
	return !OK;
    }

    /* User interface */
    printf("Please enter a natural number:\n");
    /* if the scan got 1 number */
    if(scanf("%d", &n) == SUCCESS){
    /* if the number is not natural - print an error and end program */
        if(n < 0){
            fprintf(stderr, "The number should be natural\n");
            return !OK;
        }
        else{
    /* if n is 0, make 1 link */
	    if(n == 0){
		n = 1;
	    }
	    fib_list = (int *) malloc(sizeof(int)*n);
    /* if memory allocation didn't work - print error and end program */
	    if(fib_list == NULL){
	    	printf("Memory not allocated.\n");
        	exit(!OK);
	    }
    /* use get_fibon to get the sequence */
            fib_list = get_fibon(n);
    /* initialize head to point to null */
            head = NULL;
    /* make n links and give them a value based on fib_list, and next link */
            for(i=0; i<n; i++){
                curr = (Link *) malloc(sizeof(Link));
		if(curr == NULL){
	    		printf("Memory not allocated.\n");
        		exit(!OK);
	    	}
                curr->val = fib_list[i];
                curr->next = head;
                head = curr;
            }
    /* return to the first link */
            for(i=0; i<n-1; i++){
                curr = curr->next;
            }
	    /* connecting tail to head */
            curr->next = head;

	    /* start printing from the head */
            curr = head;
    /* add informative headline to the file */
	    fprintf(ifp, "This program gets a natural number n from the user, makes a fibonacci circular linked-list, prints and saves it to a file:\n");
	    fprintf(ifp, "Entered: %d\n", n);
    /* print and add list to the file */
            for(i=0; i<n; i++){
                printf("%d\n", curr->val);
		fprintf(ifp, "%d\n", curr->val);
		curr = curr->next;
            }
        }
    }
    /* the user didn't enter a number - error and end program */
    else {
        fprintf(stderr, "The given input is not a number\n");
        return !OK;
    }
    /* free memory allocations and close file */
    free(curr);
    free(fib_list);
    fclose(ifp);
    return OK;
}

int *get_fibon(int n){
    int* list;
    int i;
    list = (int *) malloc(sizeof(int)*n);
    if(list == NULL){
	    printf("Memory not allocated.\n");
        exit(!OK);
    }
    list[0] = 1;
    list[1] = 1;
    for(i = 2; i < n; i++){
        list[i] = list[i-1] + list[i-2];
    }
    return list;
}

