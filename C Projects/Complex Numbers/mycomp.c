#include <stdio.h>
#include <string.h>
#include "complex.h"

/* PROBLEM - print_comp doesnt print the needed complex num - doesn't get into the if marked*/
int main() {
    int i, option = DEFAULT_OPT, comp_num = 0;
    char line[MAX_LINE_LEN];
    char *command;
    char *cmd, *comm, *args[3], *tmp;
    double first, second;
    bool comp_letter = false;
    complex *comp_arr[COMPS_LEN];
    enum cmdEnum {
        Read_comp, Print_comp, Add_comp, Sub_comp,
        Mult_comp_real, Mult_comp_img, Mult_comp_comp,
        Abs_comp, Stop, Null
    };
    const char *cmdArr[] = {"read_comp", "print_comp",
                            "add_comp", "sub_comp",
                            "mult_comp_real", "mult_comp_img", "mult_comp_comp", "abs_comp",
                            "stop", '\0'};
    char *comps[] = {"A", "B", "C", "D", "E", "F", '\0'};
    /*initialize A-F with 0 + 0i */
    cmd = (char *) malloc(MAX_LINE_LEN);
    comm = (char *) malloc(MAX_LINE_LEN);
    tmp = (char *) malloc(MAX_LINE_LEN);
    for (i = 0; i < COMPS_LEN; i++) {
        comp_arr[i] = init_comp();
    }
    for (i = 0; i < 3; i++) {
        args[i] = (char *) malloc(MAX_LINE_LEN);
    }
    print_menu();
    wait_for_response();
    /*add interactions with user */
    while (fgets(line, MAX_LINE_LEN, stdin) != NULL) {
	for(i = 0; i < 3; i++){
		args[i] = '\0';
	}
        strcpy(comm, line);
        if (strcmp(comm, "\n") == 0) {
            break;
        }
        printf("\ninput:%s\n", line);
        command = get_command(line, command, args);
        if (command != NULL) {
            for (i = 0; i < CMD_ARR_LEN; i++) {
                if (strcmp(command, cmdArr[i]) == 0) {
                    option = i;
                    break;
                }
            }
            if (option == Null) {
                printf("Undefined command name");
            }
        }
        if (option != DEFAULT_OPT) {
            for (i = 0; i < 6; i++) {
		/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
                if (strcmp(args[0], comps[i]) == 0) {
		    comp_num = i;
                    comp_letter = true;
		    break;
                }
            }
            if (!comp_letter) {
                printf("Undefined complex variable\n");
            } else {
                switch (option) {
                    case (Read_comp):
                        strcpy(tmp, args[1]);
                        if (check_arg(tmp)) {
                            printf("Missing parameter\n");
                            break;
                        }
                        first = strtod(tmp, &comm);
                        if (tmp == comm) {
                            printf("Invalid parameter – not a number\n");
                        } else {
                            comm = remove_spaces(comm);
                            if (strcmp(comm, "") != 0) {
                                printf("Extraneous text after end of command\n");
                                break;
                            }
                            strcpy(tmp, args[2]);
                            if (check_arg(tmp)) {
                                printf("Missing parameter\n");
                                break;
                            }
                            second = strtod(tmp, &comm);
                            comm = remove_spaces(comm);
                            if (strcmp(comm, "") != 0) {
                                printf("Extraneous text after end of command\n");
                                break;
                            } else {
                                read_comp(first, second, comp_arr[comp_num]);
                            }
                        }
                        break;
                    case (Print_comp):
			if(args[1] != NULL){
				printf("Extraneous text after end of command");
				break;
			} 
			else{
				printf("comp_num is %d\n", comp_num);
				print_comp(comp_arr[comp_num]);
			}
                        break;
                    case (Add_comp):
                        /* fill */
                        break;
                    case (Sub_comp):
                        /* fill */
                        break;
                    case (Mult_comp_real):
                        /* fill */
                        break;
                    case (Mult_comp_img):
                        /* fill */
                        break;
                    case (Mult_comp_comp):
                        /* fill */
                        break;
                    case (Abs_comp):
                        /* fill */
                        break;
                    case (Stop):
                        printf("The program ended\n");
                        break;
                    case (Null):
                    default:
                        printf("Error occurred\n");

                }
                if (option == Stop || option == Null) {
                    break;
                }
            }
        }
    }

    return 0;
}


