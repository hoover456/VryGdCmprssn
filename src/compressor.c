#include <stdio.h>

int* compress(char* cleanedInput);
char* clean(FILE *fd);
int countCharacters(FILE *fd);



int main(int argc, char* argv[]){
	if(argc>1){
		char* fileName = argv[1];
		FILE *fd;
		fd =fopen(fileName, "r");
		printf("/s",clean(fd));
	}
	return 0;
}

int countCharacters(FILE *fd){
	char c;
	int count = 0;
	while((c=fgetc(fd))!=EOF){
		if(c>=32)
			count++;
	}
	return count;
}

char* clean(FILE *fd){
	char c;
	char cleanedInput[countCharacters(fd)];
	int i = 0;

	while((c=fgetc(fd))!=EOF){
		switch(c){
			case(9):
				c=32; //replace TAB with SAPCE
				break;
			case(33):
				c=46; //replace ! with . (BE QUIETER)
				break;
			case(91): //replace { and [ with (
			case(123):
				c=40;
				break;
			case(93): //replace } and ] with )
			case(125):
				c=41;
				break;
		}
		if(c>=65 && c <=90) // TO LOWERCASE
			c+=32;
		if(c>=32)
			cleanedInput[i++]=c; //Add char c to cleaned string
	}	
}
