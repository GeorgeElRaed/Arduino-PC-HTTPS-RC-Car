#include <ESP8266WiFi.h>


const char* ssid = "JixLapTop"; //your WiFi Name
const char* password = "ELIAAMAR";  //Your Wifi Password
int ledPin = LED_BUILTIN; 
WiFiServer server(80);
int value2=0;
void setup() {
  Serial.begin(115200);
  delay(10); 
  
  pinMode(ledPin, OUTPUT);
  pinMode(D1, OUTPUT);
  pinMode(D2, OUTPUT);
  pinMode(D3, OUTPUT);
  
  digitalWrite(ledPin, LOW); 
  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid); 
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected"); 
  server.begin();
  Serial.println("Server started");
  Serial.print("Use this URL to connect: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/"); 
}

void loop() {


  WiFiClient client = server.available();
  if (!client) {
    return;
  }
  Serial.println("new client");
  while(!client.available()){
    delay(1);
  } 
  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush(); 
  int value = LOW;
  if (request.indexOf("/A") != -1)  {
    digitalWrite(D0,HIGH);
    digitalWrite(D1,LOW);
  }
  if (request.indexOf("/W") != -1)  {
    digitalWrite(D2,HIGH);
    digitalWrite(D3,LOW);
  }
  if (request.indexOf("/D") != -1)  {
    digitalWrite(D0,LOW);
    digitalWrite(D1,HIGH);
  }
  if (request.indexOf("/S") != -1)  {
    digitalWrite(D2,LOW);
    digitalWrite(D3,HIGH);
  }
  if (request.indexOf("/StopMove") != -1)  {
    digitalWrite(D2,LOW);
    digitalWrite(D3,LOW);
  }
  if (request.indexOf("/StopTurn") != -1)  {
    digitalWrite(D0,LOW);
    digitalWrite(D1,LOW);
  }
  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html");
  client.println(""); 
  client.println("");
  client.println("");
  client.print("Led is : "); 
  if(value == HIGH) {
    client.print("On");
  } else {
    client.print("Off");
  }  client.println("");    
  client.println(" ");
     delay(1);
   Serial.println("Client disonnected");
  Serial.println("");   }
//code copied from link
