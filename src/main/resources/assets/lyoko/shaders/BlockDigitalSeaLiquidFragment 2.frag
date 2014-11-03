varying vec2 uv1;
varying vec2 uv2;
varying vec3 normal;
varying vec3 position;
varying vec3 lightVector;
varying vec3 tangentLightVector;
varying vec3 tangentPosVector;

uniform sampler2D texture;
uniform sampler2D normalTexture;

uniform vec3 lightPos;
uniform vec3 mAmbient;
uniform vec3 mDiffuse;
uniform vec3 mSpecular;
uniform float shininess;

uniform vec3 lAmbient;
uniform vec3 lDiffuse;
uniform vec3 lSpecular;

void main(void) {
	vec3 lightPos2 = vec3(gl_ModelViewMatrix * vec4(lightPos, 1.0));
     vec3 texcolor = vec3(texture2D(texture, uv1));
     float dist = length(position-lightPos2);
     float att = 1.0 / (1.0 + 0.01 * dist + 0.001 * dist * dist);
     vec3 ambient = texcolor * lAmbient;
       
     vec3 surf2light = normalize(tangentLightVector);
     vec3 norm = normalize(texture2D(normalTexture, uv1).xyz * 2.0 - 1.0);
     float dcont = max(0.0, dot(norm, surf2light));
     vec3 diffuse = dcont * (texcolor * lDiffuse);
       
     vec3 surf2view = normalize(tangentPosVector);
     vec3 reflection = reflect(-surf2light, norm);
     
	float scont = pow(max(0.0, dot(surf2view, reflection)), shininess);
     vec3 specular = scont * lSpecular * mSpecular;
       
      gl_FragColor = vec4((ambient+diffuse+specular) * att, 1.0);
}