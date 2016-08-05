#ifdef GL_ES 
					#define LOWP lowp 
					precision mediump float; 
					#else 
					#define LOWP  
					#endif 
					varying LOWP vec4 vColor; 
					varying vec2 vTexCoord; 
					uniform sampler2D u_texture; 
					uniform float resolution; 
					uniform float radius; 
					uniform vec2 dir; 
					void main() { 
						vec4 sum = vec4(0.0); 
						vec2 tc = vTexCoord; 
						float blur = radius/resolution;  					   
					    float hstep = dir.x; 
					    float vstep = dir.y; 		
						sum += texture2D(u_texture, vec2(tc.x, tc.y - 0.009765625*vstep*blur)) * 0.05; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y - 0.00732421875*vstep*blur)) * 0.09; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y - 0.0048828125*vstep*blur)) * 0.12; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y - 0.00244140625*vstep*blur)) * 0.15; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y - 0.009765625*vstep*blur)) * 0.08; 
						sum += texture2D(u_texture, vec2(tc.x+0.009765625*vstep*blur, tc.y - 0.001765625*vstep*blur)) * 0.03; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y)) * 0.16; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y + 0.00244140625*vstep*blur)) * 0.15; 
						sum += texture2D(u_texture, vec2(tc.x-0.009765625*vstep*blur, tc.y +0.004765625*vstep*blur )) * 0.07; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y + 0.0048828125*vstep*blur)) * 0.12; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y + 0.00732421875*vstep*blur)) * 0.09; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y + 0.00732421875*vstep*blur)) * 0.01; 
						sum += texture2D(u_texture, vec2(tc.x, tc.y + 0.009765625*vstep*blur)) * 0.05; 
						gl_FragColor = vColor * vec4(sum.rgb, sum.a); +
					};