#include <stdio.h>
#include <string.h>
#include <math.h>
#include "complex.h"


/* setting a complex number's value */
void read_comp(double real, double imag, complex* comp){
    comp->real = real;
    comp->imag = imag;
}

/* printing a complex number */
void print_comp(complex* comp){
    printf("%.2f + (%.2f)i\n", comp->real, comp->imag);
}

/* adding 2 complex numbers and printing the sum */
void add_comp(complex* comp1, complex* comp2){
    complex* tmp = init_comp();
    tmp->real = comp1->real + comp2->real;
    tmp->imag = comp1->imag + comp2->imag;
    print_comp(tmp);
}

/* subtracting 2 complex numbers and printing the product */
void sub_comp(complex* comp1, complex* comp2){
    complex* tmp = init_comp();
    tmp->real = comp1->real - comp2->real;
    tmp->imag = comp1->imag - comp2->imag;
    print_comp(tmp);
}

/* multiplying a real number with a complex number and printing it */
void mult_comp_real(double real, complex* comp){
    complex* tmp = init_comp();
    tmp->real = (comp->real * real);
    tmp->imag = comp->imag * real;
    print_comp(tmp);
}

/* multiplying an imaginary number with a complex number and printing it */
void mult_comp_img(double imag, complex* comp){
    complex* tmp = init_comp();
    tmp->real = -(comp->imag * imag);
    tmp->imag = comp->real * imag;
    print_comp(tmp);
}

/* multiplying 2 complex numbers and printing it */
void mult_comp_comp(complex* comp1, complex* comp2){
    complex* tmp = init_comp();
    tmp->real = (comp1->real * comp2->real) - (comp1->imag * comp2->imag);
    tmp->imag = (comp1->real * comp2->imag) + (comp1->imag * comp2->real);
    print_comp(tmp);
}

/* printing the absolute value of a complex number */
void abs_comp(complex* comp){
    double a = pow(comp->real, SQUARED);
    double b = pow(comp->imag, SQUARED);
    double val = sqrt(a + b);
    printf("%.2f", val);
}





