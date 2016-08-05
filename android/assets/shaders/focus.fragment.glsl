#version 120
			            #ifdef GL_ES 
	                    precision mediump float; 
	                    #endif 
	                    uniform sampler2D u_texture; 
	                    uniform mat4 u_projTrans; 
	                    varying vec4 v_color; 
	                    varying vec2 v_texCoords; 
	                    varying float vi;      
	                    vec4 gauss(vec4 sum,vec2 tc,float hstep,float vstep,float blur)
	                     {
	    	             float scl=0.5; 
							sum += texture2D(u_texture, vec2(tc.x - 10.0*blur*hstep, tc.y -10.0* blur*vstep)) *0.011343230550777452*scl;
							sum += texture2D(u_texture, vec2(tc.x - 9.0*blur*hstep, tc.y - 9.0*blur*vstep)) *0.01653502143678369*scl;
							sum += texture2D(u_texture, vec2(tc.x - 8.0*blur*hstep, tc.y - 8.0*blur*vstep)) *0.02316565341644556*scl;
							sum += texture2D(u_texture, vec2(tc.x - 7.0*blur*hstep, tc.y -7.0* blur*vstep)) *0.031192927508010733*scl;
							sum += texture2D(u_texture, vec2(tc.x -6.0* blur*hstep, tc.y - 6.0*blur*vstep)) *0.040368211305002434*scl;
							sum += texture2D(u_texture, vec2(tc.x - 5.0*blur*hstep, tc.y - 5.0*blur*vstep)) *0.05021051387628025*scl;
							sum += texture2D(u_texture, vec2(tc.x - 5.0*blur*hstep, tc.y -5.0* blur*vstep)) *0.06002354135750171*scl;
							sum += texture2D(u_texture, vec2(tc.x - 4.0*blur*hstep, tc.y - 4.0*blur*vstep)) *0.06896366808548018*scl;
							sum += texture2D(u_texture, vec2(tc.x - 3.0*blur*hstep, tc.y - 3.0*blur*vstep)) *0.07615367743423589*scl;
							sum += texture2D(u_texture, vec2(tc.x - 2.0*blur*hstep, tc.y - 2.0*blur*vstep)) *0.08082267055801934*scl;
							
							sum += texture2D(u_texture, vec2(tc.x  + 1.5*blur*hstep, tc.y + 1.5*blur*vstep)) *0.08244176894292532*scl;
							
								                 
							sum += texture2D(u_texture, vec2(tc.x+ 2.0*blur*hstep, tc.y +2.0* blur*vstep)) *0.08082267055801937*scl;
							sum += texture2D(u_texture, vec2(tc.x + 3.0*blur*hstep, tc.y+ 3.0* blur*vstep)) *0.0761536774342359*scl;
							sum += texture2D(u_texture, vec2(tc.x + 4.0*blur*hstep, tc.y+  4.0*blur*vstep)) *0.06896366808548021*scl;
							sum += texture2D(u_texture, vec2(tc.x + 5.0*blur*hstep, tc.y + 5.0*blur*vstep)) *0.06002354135750172*scl;
							sum += texture2D(u_texture, vec2(tc.x + 5.0*blur*hstep, tc.y+ 5.0* blur*vstep)) *0.050210513876280255*scl;
							sum += texture2D(u_texture, vec2(tc.x + 6.0*blur*hstep, tc.y+ 6.0* blur*vstep)) *0.04036821130500246*scl;
							sum += texture2D(u_texture, vec2(tc.x+ 7.0* blur*hstep, tc.y+  7.0*blur*vstep)) *0.03119292750801075*scl;
							sum += texture2D(u_texture, vec2(tc.x + 8.0*blur*hstep, tc.y+ 8.0* blur*vstep)) *0.023165653416445574*scl;
							sum += texture2D(u_texture, vec2(tc.x + 9.0*blur*hstep, tc.y+ 9.0* blur*vstep)) *0.016535021436783703*scl;
							sum += texture2D(u_texture, vec2(tc.x + 10.0*blur*hstep, tc.y + 10.0*blur*vstep)) *0.011343230550777461*scl;
	                        return sum; 
	                    }
	                    
	                    
	                    void main() { 
	                            vec4 sum1 = vec4(0.0);
	                            vec4 sum2 = vec4(0.0);
	                            float vstep=1.0;
	                            float hstep=0.0;
	                             float blur=0.00027*vi;  
	                            vec2 tc = v_texCoords; 
	                             sum1=gauss(sum1,tc,hstep,vstep,blur); 
			                    vstep=0.0;
			                     hstep=1.0;       
	                            sum2=gauss(sum2,tc,hstep,vstep,blur); 
	                            gl_FragColor =(sum2+sum1)*vec4(1.0,1.0,1.0,1.0-0.15*vi); 
	                    };
