from socket import *
import time
import random
import select

serverName = 'localhost'
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName,serverPort))
prevMessage = ""

print("RDT Receiver: ")
while 1:

    inbox = clientSocket.recv(1024)
    message = inbox.decode('utf-8')
    if prevMessage == message: 
        print("Receiver just correctly received a duplicate message: ", message)
    else: 
        print("Receiver just correctly received a message: ", message)
    print("How do you respond? ")
    print("(1) send a correct ACK; (2) send a corrupted ACK; (3) do not send ACK; (4) send a wrong ACK")
    if message == '': break
    split_message = message.split(" ")
    #print(split_message)
    ack = split_message[0]
    seq = split_message[1]
    #print(seq)
    expected_seq = int(seq)
    if int(seq) != expected_seq:
        new_message = ack + " " + str(expected_seq)
        clientSocket.send(new_message.encode('utf-8'))
        print("Sent message ", new_message)
    
    if int(seq) == expected_seq:
        #print("Ack is ", ack, "and seq is ", seq)
        choice = random.randint(1,4)
        #print("Choice is ", choice)
        if choice == 1:
            print("Send a correct ack")
            clientSocket.send(message.encode('utf-8'))
            #print("Sent ", message.encode('utf-8'))
        elif choice == 2:
            print("Send message of bad ack")
            new_message = "Bad ACK"
            clientSocket.send(new_message.encode('utf-8'))
        elif choice == 3:
            print("Do nothing")
        elif choice == 4:
            print("Send a wrong ACK")
            if int(seq) == 0:
                tempseq = 1
            else: tempseq = 0
            new_message = ack + " " + str(tempseq)
            clientSocket.send(new_message.encode('utf-8'))

    if expected_seq == 0:
        expected_seq = 1
    else: expected_seq = 0
    prevMessage = message
    
clientSocket.close()