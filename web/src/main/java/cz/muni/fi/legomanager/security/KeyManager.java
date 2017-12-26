/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.legomanager.security;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 *
 * @author Štěpán Granát
 */
public class KeyManager {
    
    private static Key key;
    
    public static Key getKey() {
        if (key == null) {
            key = MacProvider.generateKey();
        }
        
        return key;
    }
    
}
