SELECT residents.name, residents.sname, residents.email,  apartments.number, apartments.area, builders.adress
FROM residents
         INNER JOIN residents_to_apartments ON residents.id = residents_to_apartments.resident_id
         INNER JOIN apartments ON apartments.id = residents_to_apartments.apartment_id
         INNER JOIN builders_to_apartments ON builders_to_apartments.apartment_id = apartments.id
         INNER JOIN builders ON builders.id = builders_to_apartments.builder_id
         INNER JOIN property_rights_to_residents ON property_rights_to_residents.resident_id = residents.id
         INNER JOIN property_rights ON property_rights_to_residents.property_right_id = property_rights_to_residents.id
WHERE residents.drive_into_the_territory = 1 AND property_rights_to_residents.property_right_id <= 3
  AND (SELECT COUNT(property_rights_to_residents.property_right_id) FROM property_rights_to_residents) = 1;
