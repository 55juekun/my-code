#include <stdio.h>
#include "stdafx.h"
void move(char x, char y){
	printf("%c-->%c\n", x, y);
}void hanoi(int n, char one, char two, char three){
	if (n == 1) move(one, three);
	else{
		hanoi(n - 1, one, three, two);
		move(one, three);
		hanoi(n - 1, two, one, three);
	}
}
void main(){
	int m;
	printf("input the number of disks: ");
	scanf_s("%d", &m);
	printf("the step to moving %3d disks: \n", m);
	hanoi(m, 'A', 'B', 'C');
}