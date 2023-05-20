//package com.feuji.weekendtrip.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.feuji.weekendtrip.model.City;
//import com.feuji.weekendtrip.model.Package;
//import com.feuji.weekendtrip.model.Passenger;
//import com.feuji.weekendtrip.model.Place;
//import com.feuji.weekendtrip.model.Traveller;
//import com.feuji.weekendtrip.service.WeekendTripService;
//
//@RestController
//@RequestMapping("/weekendtrip")
//public class WeekendTripController {
//
//	@Autowired
//	WeekendTripService service;
//
//	@PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> test(@RequestBody String cityName) {
//		System.out.println(cityName);
//		return ResponseEntity.ok("ok done");
//	}
//
//	@GetMapping("/getAllPack")
//	public ResponseEntity<List<Package>> getAllPackages() {
//		return ResponseEntity.ok(service.getPack());
//	}
//
//	@GetMapping("/getAllCity")
//	public ResponseEntity<List<City>> getAllCities() {
//		return ResponseEntity.ok(service.getCities());
//	}
//
//	@GetMapping("/getAllPlace")
//	public ResponseEntity<List<Place>> getAllPlaces() {
//		return ResponseEntity.ok(service.getPlaces());
//	}
//
//	@GetMapping("/getAllTraveller")
//	public ResponseEntity<List<Traveller>> getAllTraveller() {
//		return ResponseEntity.ok(service.getTraveller());
//	}
//
//	@GetMapping("/getAllPassenger")
//	public ResponseEntity<List<Passenger>> getAllPasssenger() {
//		return ResponseEntity.ok(service.getPassenger());
//	}
//
////	@GetMapping("/getAllLogin")
////	public ResponseEntity<List<Login>> getAllLogin() {
////		return ResponseEntity.ok(service.getLogin());
////	}
//
//	@ResponseBody
//	@PostMapping(value = "/savePack", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Package> savePackages(@RequestBody Package package1) {
//		return ResponseEntity.ok(service.savePackage(package1));
//	}
//
//	@ResponseBody
//	@PostMapping(value = "/saveCity", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<City> saveCities(@RequestBody City city) {
//		System.out.println(city);
//		return ResponseEntity.ok(service.saveCity(city));
//	}
//
//	@ResponseBody
//	@PostMapping(value = "/savePlace", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Place> savePlaces(@RequestBody Place place) {
//		return ResponseEntity.ok(service.savePlace(place));
//	}
//
//	@ResponseBody
//	@PostMapping(value = "/saveTraveller", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Traveller> saveTraveller(@RequestBody Traveller traveller) {
//		return ResponseEntity.ok(service.saveTraveller(traveller));
//	}
//
//	@ResponseBody
//	@PostMapping(value = "/savePassenger", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Passenger> savePassenger(@RequestBody Passenger passenger) {
//		return ResponseEntity.ok(service.savePassenger(passenger));
//	}
//
////	@ResponseBody
////	@PostMapping(value = "/saveLogin", produces = MediaType.APPLICATION_JSON_VALUE)
////	public ResponseEntity<Login> saveLogin(@RequestBody Login login) {
////		return ResponseEntity.ok(service.savelogin(login));
////	}
//
//	@DeleteMapping(value = "/deleteCity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> deletecity(@PathVariable(value = "id") long id) {
//		service.deletecityById(id);
//		return ResponseEntity.ok().body("city are inactived");
//	}
//
//	@PutMapping(value = "/updateCity/{id}")
//	public City updatecity(@PathVariable(value = "id") long id, @RequestBody City city) {
//		return service.updateCity(id, city);
//	}
//
//	@DeleteMapping(value = "/deletePack/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> deletepackage(@PathVariable(value = "id") long id) {
//		service.deletePackById(id);
//		return ResponseEntity.ok().body("package are inactivated");
//	}
//
//	@PutMapping(value = "/updatePack/{id}")
//	public Package updatepackage(@PathVariable(value = "id") long id, @RequestBody Package packages) {
//		return service.updatepack(id, packages);
//	}
//
//	@DeleteMapping(value = "/deletePlace/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> deleteplace(@PathVariable(value = "id") long id) {
//		service.deletePlaceById(id);
//		return ResponseEntity.ok().body("place are inactivated");
//	}
//
//	@PutMapping(value = "/updatePlace/{id}")
//	public Place updateplace(@PathVariable(value = "id") long id, @RequestBody Place place) {
//		return service.updatePlace(id, place);
//	}
//
//	@DeleteMapping(value = "/deleteTraveller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> deleteTraveller(@PathVariable(value = "id") long id) {
//		service.deleteTravellerById(id);
//		return ResponseEntity.ok().body("Traveller are deleted");
//	}
//
//	@PutMapping(value = "/updateTraveller/{id}")
//	public Traveller updateTraveller(@PathVariable(value = "id") long id, @RequestBody Traveller traveller) {
//		return service.updateTraveller(id, traveller);
//	}
//
//	@DeleteMapping(value = "/deletePassenger/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> deletePassenger(@PathVariable(value = "id") long id) {
//		service.deletePasssengerById(id);
//		return ResponseEntity.ok().body("passeneger are deleted");
//	}
//
//	@PutMapping(value = "/updatePassenger/{id}")
//	public Passenger updatePassenegers(@PathVariable(value = "id") long id, @RequestBody Passenger passengers) {
//		return service.updatePassenger(id, passengers);
//	}
//
////	@DeleteMapping(value = "/deleteLogin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
////	public ResponseEntity<String> deleteLogin(@PathVariable(value = "id") long id) {
////		service.deleteLoginById(id);
////		return ResponseEntity.ok().body("Login are deleted");
////	}
//
////	@PutMapping(value = "/updateLogin/{id}")
////	public Login updateLogin(@PathVariable(value = "id") long id, @RequestBody Login login) {
////		return service.updateLogin(id, login);
////	}
//
//	@ResponseBody
//	@PostMapping(value = "/savePackByCityId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Package> savePackage(@PathVariable(value = "id") long id, @RequestBody Package packages) {
//		System.out.println(packages);
//		return ResponseEntity.ok(service.savepackageById(id, packages));
//	}
//
//	@ResponseBody
//	@PostMapping(value = "/savePlaceByPackId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Place> savePlace(@PathVariable(value = "id") long id, @RequestBody Place place) {
//		System.out.println(place);
//		return ResponseEntity.ok(service.saveplaceById(id, place));
//	}
//
//	@ResponseBody
//	@PostMapping(value = "/savePassengerByTravellerId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Passenger> savePassenger(@PathVariable(value = "id") long id,
//			@RequestBody Passenger passenger) {
//		System.out.println(passenger);
//		return ResponseEntity.ok(service.savePassengerById(id, passenger));
//	}
//
//	@ResponseBody
//	@PostMapping(value = "/saveTravellerByLoginId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Traveller> saveTraveller(@PathVariable(value = "id") long id,
//			@RequestBody Traveller traveller) {
//		System.out.println(traveller);
//		return ResponseEntity.ok(service.saveTravellerById(id, traveller));
//	}
//
//	@PutMapping(value = "/activateCity/{id}")
//	public ResponseEntity<String> activateCity(@PathVariable(value = "id") long id) {
//		return ResponseEntity.ok(service.activateCity(id));
//	}
//
//	@PutMapping(value = "/activatePack/{id}")
//	public ResponseEntity<String> activatePack(@PathVariable(value = "id") long id) {
//		return ResponseEntity.ok(service.activatePack(id));
//	}
//
//	@PutMapping(value = "/activatePlace/{id}")
//	public ResponseEntity<String> activatePlace(@PathVariable(value = "id") long id) {
//		return ResponseEntity.ok(service.activatePlace(id));
//	}
//
//}
