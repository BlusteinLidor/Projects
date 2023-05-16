#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <ctype.h>
#include <stdbool.h>


#define SQUARED 2
#define COMPS_LEN 6
#define MAX_LINE_LEN 200
#define MAX_COM_LEN 14
#define CMD_ARR_LEN 10
#define DEFAULT_OPT 9

typedef struct complex{
    double real;
    double imag;
} complex;

complex* init_comp(void);

void read_comp(double real, double imag, complex* comp);
void print_comp(complex* comp);
void add_comp(complex* comp1, complex* comp2);
void sub_comp(complex* comp1, complex* comp2);
void mult_comp_real(double real, complex* comp);
void mult_comp_img(double imag, complex* comp);
void mult_comp_comp(complex* comp1, complex* comp2);
void abs_comp(complex* comp);

void wait_for_response(void);
void print_menu(void);
char *get_command(char line[], char *cmd, char *args[3]);
char *remove_spaces(char line[]);
bool check_arg(char *arg);







