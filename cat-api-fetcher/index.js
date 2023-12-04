const fs = require('fs')
const {mkdir,writeFile} = require("fs/promises");
const { Readable } = require('stream');
const { finished } = require('stream/promises');
const path = require("path");

const downloadImage = async (url, fileName) => {
    console.log('Downloading ' + url + ' to ' + fileName);
    const res = await fetch(url);
    const destination = path.resolve('.', fileName)
    console.log('destination being ' + destination);
    const fileStream = fs.createWriteStream(destination, { flags: 'wx' });
    await finished(Readable.fromWeb(res.body).pipe(fileStream));
};

const getAndSaveRandomImages = async () => {
    const imagesResponse = await fetch('https://api.thecatapi.com/v1/images/search?limit=14');
    if (imagesResponse.status !== 200) {
	throw new Error('Could not get the cats - status ' + imagesResponse.status);
    }
    const imagesJson = await imagesResponse.json();
    let i = 0;
    for (const imageReference of imagesJson) {
	const extensionPoint = imageReference.url.lastIndexOf('.');
	const extension = imageReference.url.substring(extensionPoint + 1);
	const fileName = 'cat_' + i + '.' + extension;
	await downloadImage(imageReference.url, fileName);
	i += 1;
    }
}

getAndSaveRandomImages();
