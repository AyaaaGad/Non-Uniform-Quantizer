# Non-Uniform-Quantizer
A java program to Apply the lossy Non-Uniform-Quantizer Compression/Decompression technique.

1‐ Inputs :

	grayscale image in which the red, green and blue components all have equal intensity  
	the number of quantization levels.

2‐ Compression :

	Readthe image.
	Construct the scalar quantizer (grouping and splitting till reach the required averages).
	Quantize the image using the quantizer you built 
	save the compressed image and the quantizer on file.

3‐ Decompression

	Read the compressed img and the quantizer from the file.
	Dequantize the data using the quantizer.
	Write the dequantized image on a file.

Note : the project associated with various Decompressed Images according to different trail inputs "the number of levels" ( 2.jpg , 4.jpg ,8.jpg ... etc ).
