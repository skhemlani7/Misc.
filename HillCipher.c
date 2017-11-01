#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int main(int argc, char** argv){
    //Declare variables and open files
    char hld;
    int block[9];
    int i,j,cnt,key_len,x,y,l,r,d,c,k;
    int key[9][9];
    int flag;
    FILE *ife, *ifp;
    char* fname1 = argv[1];
    char* fname2 = argv[2];
    ife = fopen(fname1, "r");
    ifp = fopen(fname2, "r");
    char pt[10000];
    char ct[10000];
    int nt[10000];
    i = 0;

    //Initalize the ciphertext integer array
    for(i = 0; i < 10000; i++)
            nt[i] = 0;

    //Scan the key length and key
    fscanf(ife,"%d",&key_len);
    printf("%d\n",key_len);
    for(i = 0; i < key_len; i++){
        for(j = 0; j < key_len; j++){
            fscanf(ife,"%d",&key[i][j]);
            printf("%d ",key[i][j]);
        }
        printf("\n");
    }
    printf("\n");
    //Reset i and j
    i = 0;
    j = 0;

    //Loop through file and store only lowercase chars
    while(!feof(ifp)){
        flag = 0;
        fscanf(ifp, "%c", &hld);
        printf("%c",hld);

        //If lc, store
        if(hld >= 97 && hld <= 122){
            pt[i] = hld;
            flag = 1;
        }
        //If uc, change to lc and store
        else if(hld >= 65 && hld <= 90){
            pt[i] = (hld + 32);
            flag = 1;
        }
        //If char was stored, go to next spot
        if(flag == 1)
            i++;
        }

    //See if pt needs padding
    cnt = i;
    int left = cnt % key_len;
    if(left != 0)
        //Pad pt with x's
        for(i = 0; i < key_len - left; i++){
            pt[cnt] = 'x';
            cnt++;
        }

    //Encrypt the plaintext using linear algebra
    //Loop through every letter using block intervals
    for (i = 0; i < strlen(pt); i += key_len) {
        for (j = 0; j < key_len; j++) {
            for (k = 0; k < key_len; k++) {
                nt[i + j] += (key[j][k] * (int)(pt[i + k] - 97));
            }
            ct[i + j] = (char) ((nt[i+j] % 26) + 97);
        }
    }
    printf("\n");
    //Print out the final ciphertext
    for(i = 0; i < strlen(ct);){
        for(j = 0; j < 80; j++){
            if(i < strlen(ct))
                printf("%c",ct[i++]);
        }
    }
}
