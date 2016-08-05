#ifdef GL_ES  
			#define LOWP lowp 
			precision mediump float;  
			#else 
			#define LOWP   
			#endif  
			varying LOWP vec4 vColor;  
			varying vec2 vTexCoord; 
			uniform sampler2D u_texture;
			uniform sampler2D u_map;       
			uniform float left;  
			 float dir=1.0;  
			void main() {  
				vec4 sum = vec4(0.0);  
				vec2 tc = vTexCoord;  
			sum = texture2D(u_texture, vec2(tc.x, tc.y ));
			    bool side = dir==1.0?tc.x>left:tc.x<left;  
			  float alpha=side?1.0:0.0;    
			
				gl_FragColor = sum; 
			};