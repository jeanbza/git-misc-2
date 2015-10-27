var gulp = require('gulp')
var concat = require('gulp-concat')
var uglify = require('gulp-uglify')
var del = require('del')
var path = require('path')
var browserify = require('browserify')
var babelify = require('babelify')
var source = require('vinyl-source-stream')
var buffer = require('vinyl-buffer')

gulp.task('browserify:js', ['clean:js'], function() {
    var extensions = ['.js', '.json', '.es6', '.jsx']
    var opts = {
        debug: true,
        fullPaths: true,
        extensions: extensions
    }

    return browserify(opts)
        .transform(babelify.configure({
            extensions: extensions,
            compact: false
        }))
        .require('./src/app.js', {entry: true})
        .bundle()
        .pipe(source('./app.js'))
        .pipe(gulp.dest('dist/js'))
});

gulp.task('clean:js', function() {
    return del(['dist/js/*', 'dist/js'])
})

gulp.task('default', ['clean:js', 'browserify:js'])
