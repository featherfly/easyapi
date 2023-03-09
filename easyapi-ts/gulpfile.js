const gulp = require('gulp');
const del = require('del');

var rename=require('gulp-rename');

const uglify = require('gulp-uglify');
const ts = require('gulp-typescript');

const babel = require('gulp-babel');

const { copyFile } = require('fs');
const tsProject = ts.createProject('tsconfig.json');

var fs = require('fs');

const releaseDir = "release";
const releasePackageFile = releaseDir + "/package.json";
const distDir = "release/dist";
const libDir = "release/lib";

function getPackageJson() {
  console.log('----------------------1.开始读取package.json')
  var _packageJson = fs.readFileSync('./package.json')
  console.log('----------------------读取package.json文件完毕')
  return JSON.parse(_packageJson)
}

function writePackageJson(cbDataPackage, wholeVersion) {
  console.log('----------------------4. 开始修改package.json文件')
  cbDataPackage.version = wholeVersion
  fs.writeFile(releasePackageFile, JSON.stringify(cbDataPackage,undefined,2), function (err) {
    if (err) console.error(err);
    console.log('----------------------修改package.json文件完毕，version修改为：', cbDataPackage.version)
  });
}

function rewriteVersion(append) {
    var packageData = getPackageJson();
    writePackageJson(packageData, packageData.version + append);
}

const uglifyTask = (done) => {
    gulp.src('release/lib/**/*.js') // 1. 找到文件
        .pipe(uglify()) // 2. 压缩文件
        .pipe(gulp.dest(distDir)); // 3. 另存压缩后的文件
    done();
};

const clean = (done) => {
    del(['release', 'build'], done);

    done();
};

const cleanCompile = (done) => {
    del(['build'], done);
    done();
};

const compile = (done) => {
    // tsProject.src().pipe(tsProject()).js.pipe(gulp.dest('build'));
    tsProject.src().pipe(tsProject()).js.pipe(babel({
        presets: ['@babel/preset-env']
    }))
    .pipe(gulp.dest('build'))

    done();
};
const compile2 = (done) => {
    tsProject.src().pipe(tsProject()).js.pipe(gulp.dest('build2'));

    done();
};


const copyUni = (done) => {
    // gulp.src(['./build/api.js']).pipe(gulp.dest(libDir));
    gulp.src(['./build/uni-request.js']).pipe(rename({basename:"request"})).pipe(gulp.dest(libDir));

    rewriteVersion("u");

    done();
};

const copyAxios = (done) => {
    // gulp.src(['./build/api.js']).pipe(gulp.dest(libDir));
    gulp.src(['./build/axios-request.js']).pipe(rename({basename:"request"})).pipe(gulp.dest(libDir));

    rewriteVersion("");

    done();
};

const copyReleaseFile = (done) => {
    gulp.src(['./src/*.d.ts']).pipe(gulp.dest(distDir)).pipe(gulp.dest(libDir));
    gulp.src(['index.js', 'index.mjs', 'package.json', 'README.md']).pipe(gulp.dest(releaseDir));
    done();
};

exports.clean = clean;
exports.compile = compile;
exports.compile2 = compile2;
exports.uglify = uglifyTask;
exports.copyUni = copyUni;
exports.copyAxios = copyAxios;
exports.cleanCompile = cleanCompile;
exports.copyRelease = copyReleaseFile;
exports.buildUni = gulp.series(copyReleaseFile, compile, copyUni, uglifyTask);
exports.buildAxios = gulp.series(copyReleaseFile, compile, copyAxios, uglifyTask);

// exports.clean = cleanTask;
// exports.uglify = uglifyTask;
// exports.build = gulp.series(cleanTask, uglifyTask);
// exports.default = function(done) {
//     done();
// };
