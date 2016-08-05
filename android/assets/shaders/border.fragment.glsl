#ifdef GL_ES  
			#define LOWP lowp 
			precision mediump float;  
			#else 
			#define LOWP   
			#endif  
			
			varying LOWP vec4 vColor;  
			varying vec2 vTexCoord; 
		    varying vec4 position;
		    varying vec4 area;
			
			uniform sampler2D u_texture;
			/*uniform sampler2D u_map;*/
            varying float  blur;
            
            
	
				  
			void main() {
		
				vec4 color = vec4(0.0);  
				vec2 tc = vTexCoord;  
			    color = texture2D(u_texture, vec2(tc.x, tc.y ));
	
               vec4  sum=color;
   
			     float alpha=0.0;
			     
			     if(area.w>=position.x&&area.y<=position.x)
			         if(area.x<=position.y&&area.z>=position.y)
			              alpha=1.0;
			    
			
			        
				gl_FragColor =vec4(sum.rgb, color.a*alpha)*vColor; 
			
				
			};