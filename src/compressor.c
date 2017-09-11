#include <stdio.h>
#include <string.h>
#include <sys/stat.h>


int* compress(char* cleanedInput);
void clean(char* input, FILE *fd);
int countCharacters(FILE *fd);



int main(int argc, char* argv[]){
	if(argc>1){
		char* fileName = argv[1];
		FILE *fd;
		fd =fopen(fileName, "r");
		int charCount = countCharacters(fd);
		char input[charCount];
		rewind(fd);
		clean(input, fd);
		FILE *fo;
		char* newFileName = strcat(fileName,".vgc");
		if(stat(newFileName) == 0)
			fo = fopen(newFileName,"w+");
		else
			fo = fopen(newFileName,"ra");
		fprintf(fo, "%s", input);
		int finalCharCount = countCharacters(fo);
		printf("Initial size %d\nFinal size %d\nFile size reduced by %d %% .\n",charCount,finalCharCount, (int)((charCount-finalCharCount)/charCount)*100);
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

void clean(char* input, FILE *fd){
	char c;
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
		if(c>=32 && c != 97 && c != 101 && c != 105 && c!=111 && c != 117){
			input[i++]=c; //Add char c to cleaned string	
		}
	}
	input[i]='\0';
}
