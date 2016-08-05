#version 120
		                    attribute vec4 a_position; 
		                    attribute vec4 a_color; 
		                    attribute vec2 a_texCoord0; 
		                    uniform mat4 u_projTrans; 
		                    uniform float intesity;
		                     
		                    varying vec4 v_color; 
		                    varying float vi; 
		                    varying vec2 v_texCoords; 
	                     
	                    void main() { 
	                        v_color = a_color; 
	                        v_texCoords = a_texCoord0; 
	                        vi = intesity; 
	                        gl_Position = u_projTrans * a_position; 
	                    };