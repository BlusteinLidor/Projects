#include <stdio.h>
#include <stdlib.h>

#define OK 0

/*@TODO add defines, don't use numbers */

int *get_fibon(int n);

int main(int argc, char *argv[]) {
    /* Variable declarations */
    int n, i;
    int *fib_list;
    FILE ifp;
    struct l_list {
        int val;
        struct l_list *next;
    };
    typedef struct l_list Link;
    Link *head, *curr;

    if(argc != 2){
        fprintf(stderr, "Argument number is not valid");
        return !OK;
    }

    /* @TODO try to open file - if can't, offer an error */

    /* User interface */
    printf("Please enter a natural number:\n");
    if(scanf("%d", &n) == 1){
        if(n < 0){
            fprintf(stderr, "The number should be natural");
            return !OK;
        }
        else{
            fib_list = (int *) malloc(sizeof(int)*n);
            fib_list = get_fibon(n);
            head = NULL;
            for(i=0; i<n; i++){
                curr = (Link *) malloc(sizeof(Link));
                curr->val = fib_list[i];
                curr->next = head;
                head = curr;
            }
            for(i=0; i<n-1; i++){
                curr = curr->next;
            }
            /* connecting tail to head */
            curr->next = head;

            /* start printing from the head */
            curr = head;
            for(i=0; i<n; i++){
                printf("%d\n", curr->val);
                curr = curr->next;
            }
        }
    }
    else {
        fprintf(stderr, "The given input is not a number");
        return !OK;
    }

    return OK;
}

int *get_fibon(int n){
    int* list;
    int i;
    list = (int *) malloc(sizeof(int)*n);
    list[0] = 1;
    list[1] = 1;
    for(i = 2; i < n; i++){
        list[i] = list[i-1] + list[i-2];
    }
    return list;
}
