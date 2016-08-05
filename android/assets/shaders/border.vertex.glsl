                attribute vec4 a_position;
                attribute vec4 a_area;
				attribute vec4 a_color;
				attribute vec2 a_texCoord0; 
				attribute float a_blur;
				
				uniform mat4 u_projTrans;  
				varying vec4 vColor; 
			    varying vec2 vTexCoord;
				varying  vec4 position;
				varying  vec4 area;
				varying float blur;
				
				void main() {   
					vColor =a_color; 
					vTexCoord = a_texCoord0; 
					area=a_area;
					blur=a_blur;
					position=a_position;
					gl_Position = u_projTrans *a_position; 
				};