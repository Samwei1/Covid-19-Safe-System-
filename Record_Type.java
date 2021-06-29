/**
 * this class is for defining record type
 * record contains four aspects: unique id, postcode, age, level of infection
 */
public class Record_Type {
    int id;
    int postcode;
    int age;
    int level;


    public Record_Type(int id, int postcode, int age, int level){
        this.age = age;
        this.id =id;
        this.postcode = postcode;
        this.level = level;
    }
}
