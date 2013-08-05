var mongoose = require( 'mongoose' );
var userSchema = new mongoose.Schema({
 userid: String,
 password: String
});
mongoose.model( 'User', userSchema );
mongoose.connect( 'mongodb://localhost/mydb' );

