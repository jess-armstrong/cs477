package gmu.cs.cs477.tanibon.classes;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "nosqlnews-mobilehub-1234567890-News")

public class NewsDO {
    private String _userId;
    private List<Tanibon> _pets;
    private String _score;
    private String _userName;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }

    @DynamoDBAttribute(attributeName = "score")
    public int getScore() {
        return _score;
    }

    public void setScore(final int _score) {
        this._score = _score;
    }
    @DynamoDBAttribute(attributeName = "userName")
    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String _userName) {
        this._userName = _userName;
    }

    @DynamoDBAttribute(attributeName = "pets")
    public List<Tanibon> getPets() {
        return _pets;
    }

    public void addPet(final Tanibon _pet) {
        this._pets.add(_pet);
    }

    public void removePet(final Tanibon _pet) {
        this._pets.remove(_pet);
    }

}