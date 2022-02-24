const gulp = require('gulp');
const del = require('del');

const uglify = require('gulp-uglify');
const ts = require('gulp-typescript');
const tsProject = ts.createProject('tsconfig.json');

gulp.task("uglify", (done) => {
    gulp.src('lib/**/*.js') // 1. 找到文件
        .pipe(uglify()) // 2. 压缩文件
        .pipe(gulp.dest('dist/')); // 3. 另存压缩后的文件
    done();
});

gulp.task("clean", (done) => {
    del([
      'dist/**/*',
      'lib/**/*'
    ], done);
    done();
});

gulp.task("compile",(done) => {
    tsProject.src()
    .pipe(tsProject())
    .js.pipe(gulp.dest("dist"));
    done();
});


// exports.clean = cleanTask;
// exports.uglify = uglifyTask;
// exports.build = gulp.series(cleanTask, uglifyTask);
// exports.default = function(done) {    
//     done();
// };