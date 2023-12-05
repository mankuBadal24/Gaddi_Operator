#include<ESP8266WiFi.h>
int dir=0,right_wheel_speed=0,left_wheel_speed=0; // for direction and speed of individual wheel
int speed=100;// defaultspeed
// for  ultra sonic sensor
int echo =D5;
int trig=D6;
 
#define btndelay 1000

// for motor 1
int enableA=D5; 
int in1=D0;
int in2=D1;
// for motor 2
int enableB=D6;
int in3=D2;
int in4=D3;
WiFiClient client;
WiFiServer server(80);



void setup() {
  // put your setup code here, to run once:

  Serial.begin(115200);
  pinMode(enableA,OUTPUT);
  pinMode(enableB,OUTPUT);
  pinMode(in1,OUTPUT);
  pinMode(in2,OUTPUT);
  pinMode(in3,OUTPUT);
  pinMode(in4,OUTPUT);  
  pinMode(trig,OUTPUT);
  pinMode(echo,INPUT);
WiFi.begin("Nextup Robotics.","king@123");
while(WiFi.status()!=WL_CONNECTED)
{
  Serial.println("connecting...");
  delay(200);
}
Serial.println();
Serial.println("NODEMCU is connected succesfully");
Serial.println(WiFi.localIP());
server.begin();




}



void right_wheel(){
  if(dir==1){
  digitalWrite(in1,HIGH);
  digitalWrite(in2,LOW);
 digitalWrite(enableA,right_wheel_speed);
  }
  else if(dir==-1){
  digitalWrite(in1,LOW);
  digitalWrite(in2,HIGH);
 digitalWrite(enableA,right_wheel_speed);
  }

}



  void left_wheel(){
  if(dir==1){
  digitalWrite(in4,HIGH);
  digitalWrite(in3,LOW);
 digitalWrite(enableB,left_wheel_speed);
  }
  else if(dir==-1){
  digitalWrite(in4,LOW);
  digitalWrite(in3,HIGH);
  digitalWrite(enableB,left_wheel_speed);
  }
 
  
}

float ultra_sonic_sensor(){
  long duration;
  float measured_distance;
  digitalWrite(trig,LOW);
  delayMicroseconds(2);

  digitalWrite(trig,HIGH);
  delayMicroseconds(10);
  digitalWrite(trig,LOW);
  duration=pulseIn(echo,HIGH);
  measured_distance=duration*0.034/2;
  
//  Serial.print(distance);
//  Serial.print("cm\n");
  return measured_distance;
  

}

void forward_move(int speed){
  dir=1;
  left_wheel_speed=speed;
  right_wheel_speed=speed;

  left_wheel();
  right_wheel();
    printf("moving forward\n");
    
    

  
}
void backward_move(int speed){
  dir=-1;
  left_wheel_speed=speed;
  right_wheel_speed=speed;
  left_wheel();
  right_wheel();
  printf("moving backward\n");
}
void left_turn(int speed){
  dir=1;
  left_wheel_speed=speed;
  right_wheel_speed=speed/2;
  //left_wheel();
  right_wheel();

  printf("moving left\n");

  
}
void right_turn(int speed){
   dir=1;
  left_wheel_speed=speed;
  right_wheel_speed=speed/2;
  left_wheel();
 // right_wheel();
   printf("moving right\n");

  
}


void Brakes(){

  digitalWrite(in1,LOW);
  digitalWrite(in2,LOW);
  digitalWrite(in4,LOW);
  digitalWrite(in3,LOW);
  
}



String request;
float distance;

void loop() {
  

 distance=ultra_sonic_sensor();
  // put your main code here, to run repeatedly:
  client=server.available();
  if(client==1){
    request=client.readStringUntil('\n');
    Serial.println(request);
    request.trim();
  }
   if(request=="GET /move_forward HTTP/1.1"){
     
    forward_move(speed);
    request="";
    delay(btndelay);
    Brakes();
    }
   else if(request=="GET /move_backward HTTP/1.1"){
    backward_move(speed);
     delay(btndelay);
        Brakes();
        request="";

   }
   else if(request=="GET /turn_left HTTP/1.1"){
   left_turn(speed);
       request="";
       delay(300);
       Brakes();

   }
   else if(request=="GET /turn_right HTTP/1.1"){
    right_turn(speed);
        request="";
              delay(300);
       Brakes();


  }

   else if(request=="GET /brakes HTTP/1.1"){
    Brakes();
    printf("brakes\n");
        request="";
   

  }
    
}
 
  
