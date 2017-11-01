//------------------------------------------------------------------

// University of Central Florida

// CIS3360 - Summer 2015

// Program Author: Sarvesh Khemlani

//------------------------------------------------------------------

#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char** argv) {

    //Set command line parameters to their respectful strings.
    //And initialize variables.
    int i = 0;
    int j = 0;
    int k = 0;
    int q = 0;
    char plain_text[4991];
    char ct[4991];
    int nt[4991];
    char cipher_text[4991];
    char* file_name = argv[1];
    char* vig_key = argv[2];
    char* IV = argv[3];
    int product[11];
    int output[11];

    //char vig_key_storage[11];
    //char iv_storage[11];

    //vig_key_storage = strcpy(vig_key);
    //iv_storage =

    //Makes sure the file name argument has ".txt" at the end.
    char file_extension[4] = ".txt";
    if(strstr(file_name, file_extension) == NULL){
        strcat(file_name, file_extension);
    }

    //Storing the length of each argument on the command line.
    //int length_of_file_name = strlen(argv[1]);
    int length_of_file_name = strlen(file_name);

    //Since the length of the key is the length of each block,
    //the length of the second argument on the command line will
    //be used for the block size of the encryption.
    //int block_size = strlen(argv[2]);
    int block_size = strlen(vig_key);
    //int length_of_iv = strlen(argv[3]);
    int length_of_iv = strlen(IV);
    printf("Plaintext file name: %s\n", file_name);
    printf("Vigenere keyword: %s\n", vig_key);
    printf("Initialization vector: %s\n\n", IV);
    printf("Clean Plaintext:\n\n");

    FILE*ifp = fopen(file_name, "r");

    char hld;
    int flag;
    //Loop through file and store only lowercase chars
    while(!feof(ifp)){
        flag = 0;
        fscanf(ifp, "%c", &hld);
        //printf("%c",hld);

        //If lc, store
        if(hld >= 97 && hld <= 122){
            plain_text[i] = hld;
            flag = 1;
        }
        //If uc, change to lc and store
        else if(hld >= 65 && hld <= 90){
            plain_text[i] = (hld + 32);
            flag = 1;
        }
        //If char was stored, go to next spot
        if(flag == 1)
            i++;
        }


    for(q = 0; q < i; q++){
        printf("%c",plain_text[q]);
        j++;
    }



    //Check if the number of characters is divisible by the block length.
    //If not, pad it with x's.
    int pad = i;
    int left = pad % block_size;
    if(left != 0)
        for(i = 0; i < block_size - left; i++){
            plain_text[pad] = 'x';
            pad++;
        }

    printf("\n\nCiphertext:\n\n");

    //Keeps track of padded chars.
    int padded_chars = 0;
    if(j%block_size != 0){
        padded_chars = block_size-(j%block_size);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////

    for(i = 0; i < strlen(plain_text); i++)
        nt[i] = (plain_text[i] - 97);

    int IV2[11];
    int secret2[11];

    for(i = 0; i < block_size; i++){
        IV2[i] = (int)(IV[i] - 97);
        secret2[i] = (int)(vig_key[i] - 97);
    }
    for (i = 0; i < strlen(plain_text); i += block_size) {
        for (k = 0; k < block_size; k++) {
            product[k] = nt[i+k] + (IV2[k]);
            product[k] = product[k] % 26;

            output[k] = product[k] + secret2[k];
            output[k] = output[k] % 26;

            IV2[k] = output[k];
            ct[i + k] = (char)(output[k] + 97);
        }
    }

    for(i = 0; i < j + padded_chars; i++){
            printf("%c",ct[i]);

    }

//////////////////////////////////////////////////////////////////////////////////////////////////



    printf("\n\nNumber of characters in the clean plaintext file: %d\n", j);
    printf("Block size: %d\n", block_size);
    printf("Number of pad characters added: %d\n\n", padded_chars);
    fclose(ifp);
    return 0;
}
