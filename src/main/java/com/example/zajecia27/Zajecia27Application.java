package com.example.zajecia27;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Zajecia27Application {
//	1)jak zdobyc id uzytkownika zalogowanego? // ZROBIONE w PatientController
//	2)Liquibase? dane sa z hibernate // ZROBIONE zmienione ddl na validate
//	3)potwierdzenie mailowe //ZROBIONE
//	4)odwolywanie wizyty czyli jak zrobic delete  // ZROBIONE czy tak moze byc? co z DeleteMapping

//	1)VALIDATE
//  -Walidacja wymagania na obiektach command? czy domain?
//	2)SECURITY
//	- Czym sie roznia ktorej sie uzywa? Secured Preauthorize Postauthorize?
//	- jak sie uzywa? zabezpiecza sie przed metodami czy w securityconfig?
//	-adnotacja EnableWebSecurity? do konfigurowania SecurityFilterChain?
//	- zawiera EnableGlobalAuthentication do konfigurowania AuthenticationManagerBuilder
//	-zrodlo danych dla autentykacji->
//	->czy jest potrzeba wstrzykiwania user detail service do SecurityConfig ? czy wystarczy samo utworzenie klasy UDSimpl?
//	3)MVC
//	- Adnotacja Model Attribute?
//	- problem z delete i metodami - wyjasnic
//	- jak obiekty sie wiaza ze soba? thymeleaf mvc
//	4) OBSLUGA WYJATKOW
//	- w GlobalExceptionHandler zwracamy widoki?
//	- BindingResult tylko do bledow walidacji czy tez innych?
//	- jak wyswietlac odrazu w formularzu?
//	5) JWT

//	8)bootstrap, JS
//	9) Paginacja

	//	ZADANIE DOMOWE:
//	na obiektach doctor, patient, visit
//stworz ladna aplikacje z frontem html:
// okno nr1 wybor jako kto chcesz sie zalogowac: doctor czy pacjent
// jesli dokona sie wyboru to pojawia sie kolejny widok nr2 z oknem logowania
// widok nr3 powitanie usera z odpowiednim menu w zaleznosci kto sie zalogowal (inne menu dla doctora i pacjenta)
//MENU PACJENTA:
//a) mozliowsc umowienia wizyty (do jakiego doctora, na kiedy i ktora godzine UWAGA ten doktor musi miec wtedy miejsce. jesli nie ma to powinno sie wyswietlic okienko informacyjne z błędem)
//b) mozliwosc wyswietlenia umowionych wizyt
//c) mozliowsc odwolania wizyty

//MENU DOCTORA
//a) wyswietlanie umowionych wizyt (kiedy, o ktorej, z jakim pacjentem)

//* DODATKOWO: rozszerz aplikacje wedle swoich upodoban oraz ciekawych pomyslow, dodaj potwierdzenie umowionej wizyty w formie wyslania maila na poczte GMAIL przez aplikacje
//* wszystko zrob na ladnych bootstrapowych tabelkach zeby wszystko bylo ladne i przejrzyste


	public static void main(String[] args) {
		SpringApplication.run(Zajecia27Application.class, args);
	}


}
