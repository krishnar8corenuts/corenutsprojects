//package com.feuji.weekendtrip.serviceImplementation;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.feuji.weekendtrip.model.City;
////import com.feuji.weekendtrip.model.Login;
//import com.feuji.weekendtrip.model.Package;
//import com.feuji.weekendtrip.model.Passenger;
//import com.feuji.weekendtrip.model.Place;
//import com.feuji.weekendtrip.model.Traveller;
//import com.feuji.weekendtrip.repository.CityRepository;
////import com.feuji.weekendtrip.repository.LoginRepository;
//import com.feuji.weekendtrip.repository.PackageRepository;
//import com.feuji.weekendtrip.repository.PassengerRepository;
//import com.feuji.weekendtrip.repository.PlaceRepository;
//import com.feuji.weekendtrip.repository.TravellerRepository;
//import com.feuji.weekendtrip.service.WeekendTripService;
//
//@Service
//public class WeekendTripServiceImpl implements WeekendTripService {
//
//	@Autowired(required = true)
//	CityRepository cityRepository;
//
//	@Autowired(required = true)
//	PackageRepository packageRepository;
//
//	@Autowired(required = true)
//	PlaceRepository placeRepository;
//
//	@Autowired(required = true)
//	TravellerRepository travellerRepository;
//
//	@Autowired(required = true)
//	PassengerRepository passengerRepository;
//
////	@Autowired(required = true)
////	LoginRepository loginRepository;
//
//	@Override
//	public City saveCity(City city) {
//		return cityRepository.save(city);
//	}
//
//	@Override
//	public Place savePlace(Place place) {
//		List<Package> packages = packageRepository.findAll();
//		for (Package package1 : packages) {
//			if (package1.getPackageId().equals(place.getPackages().getPackageId())) {
//				place.setPackages(package1);
//			}
//		}
//		return placeRepository.save(place);
//	}
//
//	@Override
//	public List<City> getCities() {
//		return cityRepository.findAll();
//	}
//
//	@Override
//	public List<Place> getPlaces() {
//		return placeRepository.findAll();
//	}
//
//	@Override
//	public Package savePackage(Package package1) {
//		List<City> cities = cityRepository.findAll();
//		for (City citi1 : cities) {
//			if (citi1.getCityId().equals(package1.getCity().getCityId())) {
//				package1.setCity(citi1);
//			}
//		}
//		return packageRepository.save(package1);
//	}
//
//	@Override
//	public List<Package> getPack() {
//		return packageRepository.findAll();
//	}
//
//	@Override
//	public List<Traveller> getTraveller() {
//		return travellerRepository.findAll();
//	}
//
//	@Override
//	public Traveller saveTraveller(Traveller traveller) {
//		return travellerRepository.save(traveller);
//	}
////
////	@Override
////	public Login savelogin(Login login) {
////		return loginRepository.save(login);
////	}
////
////	@Override
////	public List<Login> getLogin() {
////		return loginRepository.findAll();
////	}
//
//	@Override
//	public Passenger savePassenger(Passenger passenger) {
//		List<Traveller> traveller1 = travellerRepository.findAll();
//		for (Traveller traveller : traveller1) {
//			if (traveller.getTravellerId().equals(passenger.getTraveller().getTravellerId())) {
//				passenger.setTraveller(traveller);
//			}
//		}
//		return passengerRepository.save(passenger);
//	}
//
//	@Override
//	public List<Passenger> getPassenger() {
//		return passengerRepository.findAll();
//	}
//
//	@Override
//	public String deletecityById(long id) {
//		List<City> lis = cityRepository.findAll();
//		List<Package> pack = packageRepository.findAll();
//		List<Place> place = placeRepository.findAll();
//		for (City city : lis) {
//			if (city.getCityId().equals(id)) {
//				city.setStatus("InActive");
//			}
//			cityRepository.save(city);
//		}
//		for (Package package1 : pack) {
//			if (package1.getCity().getCityId().equals(id)) {
//				package1.setStatus("Inactive");
//			}
//			packageRepository.save(package1);
//		}
//		for (Place place2 : place) {
//			if (place2.getPackages().getStatus().equalsIgnoreCase("Inactive")) {
//				place2.setPlaceStatus("Inactive");
//			}
//			placeRepository.save(place2);
//		}
//
//		return "City Inactived" + id;
//	}
//
//	@Override
//	public City getById(long id) {
//		return cityRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public City updateCity(long id, City city) {
//		city.setCityId(id);
//		return cityRepository.save(city);
//	}
//
//	@Override
//	public String deletePackById(long id) {
//		List<Package> pack = packageRepository.findAll();
//		List<Place> place = placeRepository.findAll();
//		for (Package package1 : pack) {
//			if (package1.getPackageId().equals(id)) {
//				package1.setStatus("Inactive");
//			}
//			packageRepository.save(package1);
//		}
//		for (Place place2 : place) {
//			if (place2.getPackages().getStatus().equals("Inactive")) {
//				place2.setPlaceStatus("Inactive");
//			}
//			placeRepository.save(place2);
//		}
//		return "package Inactivated" + id;
//	}
//
//	@Override
//	public Package getPackById(long id) {
//		return packageRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public String deletePlaceById(long id) {
//		List<Place> place = placeRepository.findAll();
//		for (Place place2 : place) {
//			if (place2.getPlaceId().equals(id)) {
//				place2.setPlaceStatus("Inactive");
//			}
//			placeRepository.save(place2);
//		}
//		return "placeInactivated" + id;
//	}
//
//	@Override
//	public Place getPlaceById(long id) {
//		return placeRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public Place updatePlace(long id, Place place) {
//		place.setPlaceId(id);
//		return placeRepository.save(place);
//	}
//
//	@Override
//	public String deleteTravellerById(long id) {
//		travellerRepository.deleteById(id);
//		return "deletedtraveller" + id;
//	}
//
//	@Override
//	public Traveller getTravellerById(long id) {
//		return travellerRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public Traveller updateTraveller(long id, Traveller traveller) {
//		traveller.setTravellerId(id);
//		return travellerRepository.save(traveller);
//	}
//
//	@Override
//	public String deletePasssengerById(long id) {
//		passengerRepository.deleteById(id);
//		return "deletedpasseneger" + id;
//	}
//
//	@Override
//	public Passenger getPassengerById(long id) {
//		return passengerRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public Passenger updatePassenger(long id, Passenger passenger) {
//		passenger.setPassengerId(id);
//		return passengerRepository.save(passenger);
//	}
//
////	@Override
////	public String deleteLoginById(long id) {
////		loginRepository.deleteById(id);
////		return "deletedLogin" + id;
////	}
//
////	@Override
////	public Login getLoginById(long id) {
////		return loginRepository.findById(id).orElse(null);
////	}
////
////	@Override
////	public Login updateLogin(long id, Login Login) {
////		Login.setLoginId(id);
////		return loginRepository.save(Login);
////	}
//
//	@Override
//	public Package updatepack(long id, Package packages) {
//		packages.setPackageId(id);
//		return packageRepository.save(packages);
//	}
//
//	@Override
//	public Package savepackageById(long id, Package package1) {
//		List<City> cities = cityRepository.findAll();
//		for (City citi1 : cities) {
//			if (citi1.getCityId().equals(id)) {
//				package1.setCity(citi1);
//			}
//		}
//		return packageRepository.save(package1);
//	}
//
//	@Override
//	public Place saveplaceById(long id, Place place) {
//		List<Package> packages = packageRepository.findAll();
//		for (Package package1 : packages) {
//			if (package1.getPackageId().equals(id)) {
//				place.setPackages(package1);
//			}
//		}
//		return placeRepository.save(place);
//	}
//
//	@Override
//	public Passenger savePassengerById(long id, Passenger passengers) {
//		List<Traveller> traveller = travellerRepository.findAll();
//		for (Traveller traveller2 : traveller) {
//			if (traveller2.getTravellerId().equals(id)) {
//				passengers.setTraveller(traveller2);
//			}
//		}
//		return passengerRepository.save(passengers);
//	}
////
////	@Override
////	public Traveller saveTravellerById(long id, Traveller traveller) {
////		List<Login> login = loginRepository.findAll();
////		for (Login login2 : login) {
////			if (login2.getLoginId().equals(id)) {
////				traveller.setLogin(login2);
////			}
////		}
////		return travellerRepository.save(traveller);
////	}
//
//	@Override
//	public String activateCity(long id) {
//		List<City> lis = cityRepository.findAll();
//		List<Package> pack = packageRepository.findAll();
//		List<Place> place = placeRepository.findAll();
//		for (City city : lis) {
//			if (city.getCityId().equals(id)) {
//				city.setStatus("Active");
//			}
//			cityRepository.save(city);
//		}
//		for (Package package1 : pack) {
//			if (package1.getCity().getCityId().equals(id)) {
//				package1.setStatus("Active");
//			}
//			packageRepository.save(package1);
//		}
//		for (Place place2 : place) {
//			if (place2.getPackages().getStatus().equals("Active")) {
//				place2.setPlaceStatus("Active");
//			}
//			placeRepository.save(place2);
//		}
//
//		return "City Actived" + id;
//	}
//
//	@Override
//	public String activatePack(long id) {
//		List<Package> pack = packageRepository.findAll();
//		List<Place> place = placeRepository.findAll();
//		for (Package package1 : pack) {
//			if (package1.getPackageId().equals(id)) {
//				package1.setStatus("Active");
//			}
//			packageRepository.save(package1);
//		}
//		for (Place place2 : place) {
//			if (place2.getPackages().getStatus().equalsIgnoreCase("active")) {
//				place2.setPlaceStatus("active");
//			}
//			placeRepository.save(place2);
//		}
//		return "package Activated" + id;
//	}
//
//	@Override
//	public String activatePlace(long id) {
//		List<Place> place = placeRepository.findAll();
//		for (Place place2 : place) {
//			if (place2.getPlaceId().equals(id)) {
//				place2.setPlaceStatus("Active");
//			}
//			placeRepository.save(place2);
//		}
//		return "place activated" + id;
//	}
//
//	@Override
//	public Traveller saveTravellerById(long id, Traveller traveller) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
