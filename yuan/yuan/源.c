#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAX_WIN 20
#define MAX_STAY 100
typedef struct customer *link;
struct customer { int stay; link next; };
link GUY(int stay, link next){
	link c = malloc(sizeof *c);
	c->stay = stay; c->next = next;
	return c;
}
link win[MAX_WIN];
void morning(){
	int i;
	for (i = 0; i < MAX_WIN; i++){
		win[i] = NULL;
	}
}
void come(int w,int stay){
	if (win[w] == NULL){
		win[w] = GUY(stay, NULL);
		win[w]->next = win[w];
	}
	else win[w] = win[w]->next = GUY(stay, win[w]->next);
}
void leave(int w){
	if (win[w]->next = win[w]){
		free(win[w]);
		win[w] = NULL;
	}
	else {
		link t = win[w]->next;
		win[w]->next = t->next;
		free(t);
	}
}
void guys(){
	int i; link t;
	system("clear");
	for (i = 0; i < MAX_WIN; i++,puts("")){
		printf("Win%3d: ", i);
		if ((t = win[i]) == NULL) continue;
		for (; t->next != win[i]; t = t->next)
			printf("%4d", t->next->stay);
	}
	_sleep(1);
}
void later(){
	int i;
	for (guys(),i=0; i < MAX_WIN; i++){
		if ( win[i] == NULL) continue;
		if (win[i]->next->stay>0) (win[i]->next->stay)--;
		else leave(i);
	}
}
int main(){
	srand(time(NULL));
	for (morning();; later())
		come(rand() % MAX_WIN, rand() % MAX_STAY + 1);
	return 0;
}