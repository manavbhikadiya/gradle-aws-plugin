package jp.classmethod.aws.gradle.ec2

import groovy.lang.Lazy;
import jp.classmethod.aws.gradle.AwsPluginExtension

import org.gradle.api.Plugin
import org.gradle.api.Project

import com.amazonaws.regions.*
import com.amazonaws.services.ec2.*
import com.amazonaws.services.ec2.model.*
import com.amazonaws.services.s3.*
import com.amazonaws.services.s3.model.*


class AmazonEC2Plugin implements Plugin<Project> {
	
	void apply(Project project) {
		project.configure(project) {
			apply plugin: 'aws'
			project.extensions.create(AmazonEC2PluginExtension.NAME, AmazonEC2PluginExtension, project)
		}
	}
}

class AmazonEC2PluginExtension {
	
	public static final NAME = 'ec2'
	
	Project project
	String accessKeyId
	String secretKey
	Region region
		
	@Lazy
	AmazonEC2 ec2 = {
		AwsPluginExtension aws = project.extensions.getByType(AwsPluginExtension)
		return aws.createClient(AmazonEC2Client, region, accessKeyId, secretKey)
	}()
	
	AmazonEC2PluginExtension(Project project) {
		this.project = project;
	}
}
