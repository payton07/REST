package org.example.hotel.types;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger counter = new AtomicInteger(1);

    // Pour générer un ID unique pour les offres
    public static String generateOfferId(String prefix) {
        return "OFF-"+prefix + counter.getAndIncrement();
    }

    // Pour les réservations (ajout de timestamp pour garantir unicité)
    public static String generateReservationId() {
        return "RES-" + System.currentTimeMillis() + "-" + counter.getAndIncrement();
    }
    // Pour Hotel
    public static String generateHotelId() {
        return "HOT-" + System.currentTimeMillis() + "-" + counter.getAndIncrement();
    }
    // Pour client
    public static String generateClientId() {
        return "CLI-" + System.currentTimeMillis() + "-" + counter.getAndIncrement();
    }
    // Variante générique (UUID aléatoire)
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
