var mongoose = require('mongoose');

exports.userlist = function userlist(username,callback){
 var User = mongoose.model( 'User' );
 User.find({'userid':username}, function (err, users) {
  if(err){
   console.log(err);
  }else{
   console.log(users);
   callback("",users);
  }
 })// end Team.find
}// end exports.teamlist


