               #ifdef GL_ES 
		         precision mediump float; 
		       #endif 
				varying  vec4 vColor; 
				varying vec2 vTexCoord;  
				uniform sampler2D u_texture;
				uniform float resolution;  
				uniform float radius;  
				uniform vec2 dir;  
				void main() { 
					vec4 sum = vec4(0.0);  
					vec2 tc = vTexCoord; 
					float blur = radius/resolution;    
				    float hstep = 0.0;  
				    float vstep = 0.0; 
					sum += texture2D(u_texture, vec2(tc.x, tc.y)); 
					gl_FragColor = vColor * vec4(sum.rgb, sum.a); 
				};