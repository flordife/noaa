# Noaa

NOAA(National Oceanic and Atmospheric Administration).

API Rest que permita registrar embarcaciones desde una serie
de boyas en altamar. Cada una de estas boyas marinas tiene sensores que detectan
embarcaciones cercana y al mismo tiempo el nivel del mar actual que se encuentra la boya. Estas
embarcaciones a su vez, transmiten un código único por barco usando ondas cortas que son
detectadas por las boyas.

Las boyas constantemente transmitirán lo que llamaremos “Muestra”. También poseen un faro de
luz, que cambia de color según la siguiente tabla:
ROJO: Marea peligrosa
AMARILLO: Advertencia de marea peligrosa
VERDE: todo Ok
AZUL: indefinido

Tecnología utilizada:

Java Springboot

Deployment: Heroku: https://api-noaa.herokuapp.com/

Base de Datos:

MySql


