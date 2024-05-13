package id.ac.ui.cs.advprog.hoomgroomcommerce.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PengirimanStateConverter implements AttributeConverter<PengirimanState, String>{

    @Override
    public String convertToDatabaseColumn(PengirimanState attribute) {
        // TODO Auto-generated method stub
        if (attribute instanceof PengirimanDiprosesState){
            return "Diproses";
        } else if (attribute instanceof PengirimanDikirimState){
            return "Dikirim";
        } else if (attribute instanceof PengirimanDiterimaState){
            return "Diterima";
        } else {
            throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public PengirimanState convertToEntityAttribute(String dbData) {
        // TODO Auto-generated method stub
        switch (dbData) {
            case "Diproses":
                return new PengirimanDiprosesState();
            case "Dikirim":
                return new PengirimanDikirimState();
            case "Diterima":
                return new PengirimanDiterimaState();
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
                
        }
    }
    
}
