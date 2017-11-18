from socket import *
import time
import random
import select

data=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]

print("RDT Sender:\nLets start")
serverPort = 12000
serverSocket = socket(AF_INET,SOCK_STREAM)
serverSocket.bind(('',serverPort))
serverSocket.listen(1)
#print('The server is ready to receive')
connectionSocket, addr = serverSocket.accept()
print ('Connected by', addr)

connectionSocket.settimeout(0.2)
timeout_value = 2
seq = 0
index = 0
count = 0

while 1:
    if index == len(data): break

    message = data[index] + " " + str(seq)
    connectionSocket.send(message.encode('utf-8'))
    print("Sender sent a message: ", message)
    
    start_time = time.time()

    while 1:
        if (time.time() - start_time) > timeout_value: 
            print("Timeout, Send the message again.")
            break
        try:
            rcvMessage = connectionSocket.recv(1024)    
        except timeout:
            print('Continue waiting')
            continue

        inbox = rcvMessage.decode('utf-8')
        print("Received ", inbox)

        if inbox != "Bad ACK": 
            seqack = inbox.split(" ")
        else: 
            print("Sender received a corrupted ACK, keep waiting") 
            continue
        #print(seqack)

        if inbox == message:
            print("Sender received a valid ACK for ", seq, ", send next message")
            if seq == 0:
                seq = 1
            else: seq = 0
            index = index + 1
            break
        elif int(seqack[1]) != seq:
            print("Sender received an ACK with wrong sequency number, keep waiting")
            continue
        elif inbox == "Bad ACK":
            print("Sender received a corrupted ACK, keep waiting")
            continue

connectionSocket.close()
