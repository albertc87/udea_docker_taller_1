var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'User\'s CRUD ' });
});

router.get('/create_user', function(req, res, next) {
  res.render('create_user', { title: 'Create new user' });
});

module.exports = router;
