//Miguel López, Sergio
//Práctica 4

#include "DHT.h"

#define DHTPIN D7  //D7 pin DHT11
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

#define SOUNDLED D0 //D0 led indicador del ruido
#define SWITCH D5 //D5 pin digital para el detector de sonido
#define SOUND A0 //A0 pin analogico para el detector de sonido
int valD; //estado del detector de sonido
int valA; //estado entre 0 y 1024 del detector de sonido

#define BLUE D1 //D1 pin para el azul del led RGB
#define GREEN D2 //D2 pin para el verde del led RGB
#define RED D3 //D3 pin para el rojo del led RGB

#define BUTTON D8 //Boton para cambiar de sensor, si no esta pulsado, se recgen datos del sensor de sonido. MIentras este pulsado se muestra la temperatura y la humedad.

void setup() {
  Serial.begin(9600);
  Serial.println(F("DHT11 test!"));
  pinMode(SOUNDLED,OUTPUT);
  pinMode(SWITCH,INPUT);
  pinMode(SOUND,INPUT);
  pinMode(BUTTON,INPUT);
  pinMode(RED, OUTPUT);
  pinMode(GREEN, OUTPUT);
  pinMode(BLUE, OUTPUT);
  digitalWrite(RED, LOW);
  digitalWrite(GREEN, HIGH);
  digitalWrite(BLUE, LOW);
  ESP.wdtDisable(); //Desactivamos el watchdog
  dht.begin();
}

void loop() {

  if(digitalRead(BUTTON)==LOW){ //Si el boton no esta pulsado, se leen los datos del sensor de sonido.
    valD=digitalRead(SWITCH);
    valA=analogRead(SOUND);
    Serial.println(valA,DEC); //Imprimimos el valor
    if(valD==HIGH){ 
      digitalWrite(SOUNDLED,LOW); //Si el sensor del sonido pasa un valor determinado, se enciende el led D0
    }
    else{
      digitalWrite(SOUNDLED,HIGH);
    }
    if(valA>900){ //Si la señal analogica es alta, el led RGB se pone en rojo
      digitalWrite(RED, HIGH);
      digitalWrite(GREEN, LOW);
      digitalWrite(BLUE, LOW);
    }
    else if(valA>820){ //Si la señal analogica es media, el led RGB se pone en amarillo
      digitalWrite(RED, HIGH);
      digitalWrite(GREEN, HIGH);
      digitalWrite(BLUE, LOW);
    }
    else{ //Si la señal analogica es baja, el led RGB se pone en verde
      digitalWrite(RED, LOW);
      digitalWrite(GREEN, HIGH);
      digitalWrite(BLUE, LOW);
    }  
  }
  
  else{ //Mientras el boton este pulsado, se leen los datos del sensor de temperatura y humedad.
    digitalWrite(RED, LOW); //Ponemos el led en azul para indicar que se esta usando el sensor de temperatura y humedad.
    digitalWrite(GREEN, LOW);
    digitalWrite(BLUE, HIGH);
    delay(2000); 
  
    float h = dht.readHumidity(); // Leemos la temperatura en Celsius
    float t = dht.readTemperature(); // Leemos la temperatura en Fahrenheit
    float f = dht.readTemperature(true);
  
    if (isnan(h) || isnan(t) || isnan(f)) { // Si la lectura falla
      Serial.println(F("Failed to read from DHT sensor!"));
      return;
    }
  
    float hif = dht.computeHeatIndex(f, h); 
    float hic = dht.computeHeatIndex(t, h, false);
  
    Serial.print(F("[Miguel López - Práctica 4] ")); //Imprimimos las temperatura y la humedad.
    Serial.print(F("Humidity: "));
    Serial.print(h);
    Serial.print(F("%  Temperature: "));
    Serial.print(t);
    Serial.print(F("°C "));
    Serial.print(f);
    Serial.print(F("°F  Heat index: "));
    Serial.print(hic);
    Serial.print(F("°C "));
    Serial.print(hif);
    Serial.println(F("°F"));
  }
}
