from socket import *
serverPort = 5500
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(1)
print('The server is ready to receive')
while 1:
    connectionSocket, addr = serverSocket.accept()
    sentence = connectionSocket.recv(1024)
    capitalizedSentence = str(eval(sentence))
    connectionSocket.send(capitalizedSentence)
connectionSocket.close()
