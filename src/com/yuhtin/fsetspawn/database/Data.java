package com.yuhtin.fsetspawn.database;

import java.sql.Connection;

public interface Data {

    String table = "yhfsetspawn";

    Connection openConnection();
}
