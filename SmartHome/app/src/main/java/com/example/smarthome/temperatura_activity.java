/*
    Está actividad muestra los datos recibidos del sensor DHT11 de temperatura y humedad.
    Está actividad permite:
    ->  Conectarse al servidor MQTT elegido en Configuración
    ->  Desconectarse del servidor MQTT
    ->  Recibir los datos de temperatura y humedad del tópico elegido
    ->  Obtener los datos del servidor ingresado en Configuración
    ->  Verificar si el servidor MQTT está en la web
 */

package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class temperatura_activity extends AppCompatActivity
{
    // Variables TEMPERATURA_ACTIVITY
    private TextView temperatura;
    private TextView humedad;

    // Variables MQTT
    private MQTTClass mqttClass = MainActivity.getMqttClass();

    // ---------------> Métodos TEMPERATURA_ACTIVITY <---------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        temperatura = findViewById(R.id.textView_temp);
        humedad = findViewById(R.id.textView_hum);

        mqttClass.setCallback(new MqttCallbackExtended()
        {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception
            {
                if( topic.equals("casa/temperatura") )
                {
                    String aux = new String(message.getPayload());
                    temperatura.setText("Temperatura = " + aux + " °C");
                }
                if(  topic.equals("casa/humedad") )
                {
                    String aux = new String(message.getPayload());
                    humedad.setText("Humedad = " + aux + " %");
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
}
