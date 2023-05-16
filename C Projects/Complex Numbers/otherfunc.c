#include "complex.h"

complex* init_comp(void){
    complex *tmp = (complex *)malloc(sizeof(complex));
    if (!tmp)
    {
        printf("Memory allocation failed!");
        exit(1);
    }
    /* Default value: 0.0 for both fields */
    tmp->real = 0.0;
    tmp->imag = 0.0;
    return tmp;
}

void wait_for_response(void){
    printf("$ ");
}

void print_menu(void){
    printf("Please enter the commands you want:\n");
}

char *get_command(char line[], char *cmd, char *args[3]){
    int i = 0;
    char *tmp;
    line = remove_spaces(line);
    cmd = strtok(line, " ");
    tmp = cmd;
    while((cmd = strtok(NULL, ","))){
        args[i] = cmd;
	args[i] = remove_spaces(args[i]);
        i++;
    }
    /*for(i = 0; i < 3; i++){
	args[i] = remove_spaces(args[i]);
        args[i] = strtok(args[i], ",");
	
    }*/
    return tmp;
}

char *remove_spaces(char line[]){
    while(isspace(*line)){
        line++;
    }
    return line;
}

bool check_arg(char *arg){
	if(strcmp(arg, "") == 0 || strcmp(arg, " ") == 0 ||
 strcmp(arg, "\t") == 0 || strcmp(arg, "\n") == 0){
		return true;
	}
	return false;
}

