<#
.SYNOPSIS
A class representing a Maven dependency.

.DESCRIPTION
The Dependency class has properties for Group, Artifact, Version, Scope, and Type.
#>
class Dependency
{
    [string]$Group
    [string]$Artifact
    [string]$Version
    [string]$Scope
    [string]$Type

    Dependency([string]$group, [string]$artifact, [string]$version, [string]$scope, [string]$type)
    {
        $this.Group = $group
        $this.Artifact = $artifact
        $this.Version = $version
        $this.Scope = $scope
        $this.Type = $type
    }
}

<#
.SYNOPSIS
Retrieves the date object for a given Maven dependency.

.DESCRIPTION
This function takes a Dependency object as input and fetches the date of the dependency using the Maven Central Repository API.

.PARAMETER Dependency
The Dependency object for which the date should be fetched.

.EXAMPLE
$dateObject = $dependency | Get-DependencyDateObject
#>
function Get-DependencyDate
{
    [CmdletBinding()]
    param(
        [Parameter(Mandatory = $true, ValueFromPipeline = $true)]
        [Dependency]$dependency
    )

    PROCESS {
        $url = "https://search.maven.org/solrsearch/select?q=" +
                "g:$( $dependency.Group )+AND+" +
                "a:$( $dependency.Artifact )+AND+" +
                "v:$( $dependency.Version )&rows=1&wt=json"
        $response = Invoke-RestMethod -Uri $url
        $date = $response.response.docs[0].timestamp

        $epochStart = [DateTime]::new(1970, 1, 1, 0, 0, 0, [System.DateTimeKind]::Utc)
        return $epochStart.AddMilliseconds($date).ToLocalTime()
    }
}

<#
.SYNOPSIS
Retrieves a list of Maven dependencies with their group, artifact, version, scope, and type.

.DESCRIPTION
This function runs the 'mvn dependency:list --batch-mode' command, parses the output, and returns a list of Dependency objects.

.EXAMPLE
$dependencies = Get-MavenDependencies
#>
function Get-MavenDependencies
{
    $mavenOutput = (mvn dependency:list --batch-mode)

    $dependencies = $mavenOutput | ForEach-Object {
        if ($_ -match "^\[INFO\]    (\S+):(\S+):(\S+):(\S+):(\S+)( -- module .+)?$")
        {
            $group = $matches[1]
            $artifact = $matches[2]
            $version = $matches[4]
            $scope = $matches[5]
            $type = $matches[3]

            [Dependency]::new($group, $artifact, $version, $scope, $type)
        }
    }

    return $dependencies
}

<#
.SYNOPSIS
Calculates the average date from a list of local dates.

.DESCRIPTION
This function takes a list of local dates as input and returns the average date.

.PARAMETER Dates
An array of DateTime objects representing the local dates.

.EXAMPLE
$averageDate = $dateList | Get-AverageDate
#>
function Get-AverageDate
{
    [CmdletBinding()]
    param(
        [Parameter(Mandatory = $true, ValueFromPipeline = $true)]
        [DateTime[]]$Dates
    )

    BEGIN {
        $epochStart = [DateTime]::new(1970, 1, 1, 0, 0, 0, [System.DateTimeKind]::Utc)
        $totalDays = 0
        $dateCount = 0
    }

    PROCESS {
        foreach ($date in $Dates)
        {
            $daysSinceEpoch = ($date - $epochStart).TotalDays
            $totalDays += $daysSinceEpoch
            $dateCount++
        }
    }

    END {
        if ($dateCount -gt 0)
        {
            $averageDays = $totalDays / $dateCount
            $averageDate = $epochStart.AddDays($averageDays).ToLocalTime()
            return $averageDate
        }
        else
        {
            Write-Warning "No dates provided."
            return $null
        }
    }
}

<#
.SYNOPSIS
Calculates the period between a given date and today.

.DESCRIPTION
This function takes a DateTime object as input and returns a TimeSpan object representing the period between the given date and today.

.PARAMETER Date
The DateTime object representing the date for which the period should be calculated.

.EXAMPLE
$period = Get-Date "2000-01-01" | Get-PeriodFromDate
#>
function Get-PeriodFromDate
{
    [CmdletBinding()]
    param(
        [Parameter(Mandatory = $true, ValueFromPipeline = $true)]
        [DateTime]$Date
    )

    PROCESS {
        $currentDate = Get-Date
        $period = New-TimeSpan -Start $Date -End $currentDate
        return $period
    }
}

#$dependencies = Get-MavenDependencies
#$dependencies | Format-Table
#
## Example usage of Get-DependencyDateObject function
#$firstDependency = $dependencies[0]
#$dateObject = Get-DependencyDateObject $firstDependency
#Write-Host "Date for the first dependency: $($dateObject.ToString("yyyy-MM-dd") )"

Get-MavenDependencies |
        Get-DependencyDate |
        Get-PeriodFromDate |
        Select-Object -ExpandProperty Days |
        Measure-Object -Average |
        Select-Object -ExpandProperty Average
